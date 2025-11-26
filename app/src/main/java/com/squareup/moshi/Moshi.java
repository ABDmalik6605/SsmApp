package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public final class Moshi {
    static final List<JsonAdapter.Factory> BUILT_IN_FACTORIES;
    private final List<JsonAdapter.Factory> factories;
    private final ThreadLocal<List<DeferredAdapter<?>>> reentrantCalls = new ThreadLocal<>();
    private final Map<Object, JsonAdapter<?>> adapterCache = new LinkedHashMap();

    static {
        ArrayList arrayList = new ArrayList(5);
        BUILT_IN_FACTORIES = arrayList;
        arrayList.add(StandardJsonAdapters.FACTORY);
        arrayList.add(CollectionJsonAdapter.FACTORY);
        arrayList.add(MapJsonAdapter.FACTORY);
        arrayList.add(ArrayJsonAdapter.FACTORY);
        arrayList.add(ClassJsonAdapter.FACTORY);
    }

    Moshi(Builder builder) {
        int size = builder.factories.size();
        List<JsonAdapter.Factory> list = BUILT_IN_FACTORIES;
        ArrayList arrayList = new ArrayList(size + list.size());
        arrayList.addAll(builder.factories);
        arrayList.addAll(list);
        this.factories = Collections.unmodifiableList(arrayList);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type) {
        return adapter(type, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Class<T> cls) {
        return adapter(cls, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation> cls) {
        Objects.requireNonNull(cls, "annotationType == null");
        return adapter(type, Collections.singleton(Types.createJsonQualifierImplementation(cls)));
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation>... clsArr) {
        if (clsArr.length == 1) {
            return adapter(type, clsArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(clsArr.length);
        for (Class<? extends Annotation> cls : clsArr) {
            linkedHashSet.add(Types.createJsonQualifierImplementation(cls));
        }
        return adapter(type, Collections.unmodifiableSet(linkedHashSet));
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Set<? extends Annotation> set) {
        return adapter(type, set, null);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Set<? extends Annotation> set, @Nullable String str) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(set, "annotations == null");
        Type typeCanonicalize = Util.canonicalize(type);
        Object objCacheKey = cacheKey(typeCanonicalize, set);
        synchronized (this.adapterCache) {
            JsonAdapter<T> jsonAdapter = (JsonAdapter) this.adapterCache.get(objCacheKey);
            if (jsonAdapter != null) {
                return jsonAdapter;
            }
            List<DeferredAdapter<?>> arrayList = this.reentrantCalls.get();
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    DeferredAdapter<?> deferredAdapter = arrayList.get(i);
                    if (deferredAdapter.cacheKey.equals(objCacheKey)) {
                        return deferredAdapter;
                    }
                }
            } else {
                arrayList = new ArrayList<>();
                this.reentrantCalls.set(arrayList);
            }
            DeferredAdapter<?> deferredAdapter2 = new DeferredAdapter<>(typeCanonicalize, str, objCacheKey);
            arrayList.add(deferredAdapter2);
            int size2 = arrayList.size() - 1;
            try {
                try {
                    int size3 = this.factories.size();
                    for (int i2 = 0; i2 < size3; i2++) {
                        JsonAdapter<T> jsonAdapter2 = (JsonAdapter<T>) this.factories.get(i2).create(typeCanonicalize, set, this);
                        if (jsonAdapter2 != null) {
                            deferredAdapter2.ready(jsonAdapter2);
                            synchronized (this.adapterCache) {
                                this.adapterCache.put(objCacheKey, jsonAdapter2);
                            }
                            arrayList.remove(size2);
                            return jsonAdapter2;
                        }
                    }
                    if (size2 == 0) {
                        this.reentrantCalls.remove();
                    }
                    throw new IllegalArgumentException("No JsonAdapter for " + Util.typeAnnotatedWithAnnotations(typeCanonicalize, set));
                } catch (IllegalArgumentException e) {
                    if (size2 == 0) {
                        throw errorWithFields(arrayList, e);
                    }
                    throw e;
                }
            } finally {
                if (size2 == 0) {
                    this.reentrantCalls.remove();
                }
            }
        }
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> nextAdapter(JsonAdapter.Factory factory, Type type, Set<? extends Annotation> set) {
        Objects.requireNonNull(set, "annotations == null");
        Type typeCanonicalize = Util.canonicalize(type);
        int iIndexOf = this.factories.indexOf(factory);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Unable to skip past unknown factory " + factory);
        }
        int size = this.factories.size();
        for (int i = iIndexOf + 1; i < size; i++) {
            JsonAdapter<T> jsonAdapter = (JsonAdapter<T>) this.factories.get(i).create(typeCanonicalize, set, this);
            if (jsonAdapter != null) {
                return jsonAdapter;
            }
        }
        throw new IllegalArgumentException("No next JsonAdapter for " + Util.typeAnnotatedWithAnnotations(typeCanonicalize, set));
    }

    @CheckReturnValue
    public Builder newBuilder() {
        return new Builder().addAll(this.factories.subList(0, this.factories.size() - BUILT_IN_FACTORIES.size()));
    }

    private Object cacheKey(Type type, Set<? extends Annotation> set) {
        return set.isEmpty() ? type : Arrays.asList(type, set);
    }

    static IllegalArgumentException errorWithFields(List<DeferredAdapter<?>> list, IllegalArgumentException illegalArgumentException) {
        int size = list.size();
        if (size == 1 && list.get(0).fieldName == null) {
            return illegalArgumentException;
        }
        StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
        for (int i = size - 1; i >= 0; i--) {
            DeferredAdapter<?> deferredAdapter = list.get(i);
            sb.append("\nfor ");
            sb.append(deferredAdapter.type);
            if (deferredAdapter.fieldName != null) {
                sb.append(' ');
                sb.append(deferredAdapter.fieldName);
            }
        }
        return new IllegalArgumentException(sb.toString(), illegalArgumentException);
    }

    public static final class Builder {
        final List<JsonAdapter.Factory> factories = new ArrayList();

        public <T> Builder add(final Type type, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            }
            if (jsonAdapter == null) {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
            return add(new JsonAdapter.Factory() { // from class: com.squareup.moshi.Moshi.Builder.1
                @Override // com.squareup.moshi.JsonAdapter.Factory
                @Nullable
                public JsonAdapter<?> create(Type type2, Set<? extends Annotation> set, Moshi moshi) {
                    if (set.isEmpty() && Util.typesMatch(type, type2)) {
                        return jsonAdapter;
                    }
                    return null;
                }
            });
        }

        public <T> Builder add(final Type type, final Class<? extends Annotation> cls, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            }
            if (cls == null) {
                throw new IllegalArgumentException("annotation == null");
            }
            if (jsonAdapter == null) {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
            if (!cls.isAnnotationPresent(JsonQualifier.class)) {
                throw new IllegalArgumentException(cls + " does not have @JsonQualifier");
            }
            if (cls.getDeclaredMethods().length > 0) {
                throw new IllegalArgumentException("Use JsonAdapter.Factory for annotations with elements");
            }
            return add(new JsonAdapter.Factory() { // from class: com.squareup.moshi.Moshi.Builder.2
                @Override // com.squareup.moshi.JsonAdapter.Factory
                @Nullable
                public JsonAdapter<?> create(Type type2, Set<? extends Annotation> set, Moshi moshi) {
                    if (Util.typesMatch(type, type2) && set.size() == 1 && Util.isAnnotationPresent(set, cls)) {
                        return jsonAdapter;
                    }
                    return null;
                }
            });
        }

        public Builder add(JsonAdapter.Factory factory) {
            if (factory == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.factories.add(factory);
            return this;
        }

        public Builder add(Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("adapter == null");
            }
            return add((JsonAdapter.Factory) AdapterMethodsFactory.get(obj));
        }

        Builder addAll(List<JsonAdapter.Factory> list) {
            this.factories.addAll(list);
            return this;
        }

        @CheckReturnValue
        public Moshi build() {
            return new Moshi(this);
        }
    }

    private static final class DeferredAdapter<T> extends JsonAdapter<T> {

        @Nullable
        Object cacheKey;

        @Nullable
        private JsonAdapter<T> delegate;

        @Nullable
        final String fieldName;
        final Type type;

        DeferredAdapter(Type type, @Nullable String str, Object obj) {
            this.type = type;
            this.fieldName = str;
            this.cacheKey = obj;
        }

        void ready(JsonAdapter<T> jsonAdapter) {
            this.delegate = jsonAdapter;
            this.cacheKey = null;
        }

        @Override // com.squareup.moshi.JsonAdapter
        public T fromJson(JsonReader jsonReader) throws IOException {
            JsonAdapter<T> jsonAdapter = this.delegate;
            if (jsonAdapter == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            return jsonAdapter.fromJson(jsonReader);
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            JsonAdapter<T> jsonAdapter = this.delegate;
            if (jsonAdapter == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            jsonAdapter.toJson(jsonWriter, (JsonWriter) t);
        }
    }
}
