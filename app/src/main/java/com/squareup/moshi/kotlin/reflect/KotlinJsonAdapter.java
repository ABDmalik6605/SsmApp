package com.squareup.moshi.kotlin.reflect;

import androidx.exifinterface.media.ExifInterface;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.AbstractMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;

/* compiled from: KotlinJsonAdapter.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u001e\u001fB9\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R'\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/squareup/moshi/JsonAdapter;", "constructor", "Lkotlin/reflect/KFunction;", "bindings", "", "Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "(Lkotlin/reflect/KFunction;Ljava/util/List;Lcom/squareup/moshi/JsonReader$Options;)V", "getBindings", "()Ljava/util/List;", "getConstructor", "()Lkotlin/reflect/KFunction;", "getOptions", "()Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "(Lcom/squareup/moshi/JsonReader;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "(Lcom/squareup/moshi/JsonWriter;Ljava/lang/Object;)V", "toString", "", "Binding", "IndexedParameterMap", "moshi-kotlin"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class KotlinJsonAdapter<T> extends JsonAdapter<T> {
    private final List<Binding<T, Object>> bindings;
    private final KFunction<T> constructor;
    private final JsonReader.Options options;

    public final KFunction<T> getConstructor() {
        return this.constructor;
    }

    public final List<Binding<T, Object>> getBindings() {
        return this.bindings;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinJsonAdapter(KFunction<? extends T> constructor, List<Binding<T, Object>> bindings, JsonReader.Options options) {
        Intrinsics.checkParameterIsNotNull(constructor, "constructor");
        Intrinsics.checkParameterIsNotNull(bindings, "bindings");
        Intrinsics.checkParameterIsNotNull(options, "options");
        this.constructor = constructor;
        this.bindings = bindings;
        this.options = options;
    }

    public final JsonReader.Options getOptions() {
        return this.options;
    }

    @Override // com.squareup.moshi.JsonAdapter
    public T fromJson(JsonReader reader) throws IOException {
        Intrinsics.checkParameterIsNotNull(reader, "reader");
        int size = this.constructor.getParameters().size();
        int size2 = this.bindings.size();
        Object[] objArr = new Object[size2];
        for (int i = 0; i < size2; i++) {
            objArr[i] = KotlinJsonAdapterKt.ABSENT_VALUE;
        }
        reader.beginObject();
        while (true) {
            if (reader.hasNext()) {
                int iSelectName = reader.selectName(this.options);
                Binding<T, Object> binding = iSelectName != -1 ? this.bindings.get(iSelectName) : null;
                if (binding != null) {
                    if (objArr[iSelectName] != KotlinJsonAdapterKt.ABSENT_VALUE) {
                        throw new JsonDataException("Multiple values for '" + this.constructor.getParameters().get(iSelectName).getName() + "' at " + reader.getPath());
                    }
                    objArr[iSelectName] = binding.getAdapter().fromJson(reader);
                    if (objArr[iSelectName] == null && !binding.getProperty().getReturnType().isMarkedNullable()) {
                        throw new JsonDataException("Non-null value '" + binding.getProperty().getName() + "' was null at " + reader.getPath());
                    }
                } else {
                    reader.skipName();
                    reader.skipValue();
                }
            } else {
                reader.endObject();
                for (int i2 = 0; i2 < size; i2++) {
                    if (objArr[i2] == KotlinJsonAdapterKt.ABSENT_VALUE && !this.constructor.getParameters().get(i2).isOptional()) {
                        if (!this.constructor.getParameters().get(i2).getType().isMarkedNullable()) {
                            throw new JsonDataException("Required value '" + this.constructor.getParameters().get(i2).getName() + "' missing at " + reader.getPath());
                        }
                        objArr[i2] = null;
                    }
                }
                KFunction<T> kFunction = this.constructor;
                T tCallBy = kFunction.callBy(new IndexedParameterMap(kFunction.getParameters(), objArr));
                int size3 = this.bindings.size();
                while (size < size3) {
                    Binding<T, Object> binding2 = this.bindings.get(size);
                    if (binding2 == null) {
                        Intrinsics.throwNpe();
                    }
                    binding2.set(tCallBy, objArr[size]);
                    size++;
                }
                return tCallBy;
            }
        }
    }

    @Override // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter writer, T value) throws IOException {
        Intrinsics.checkParameterIsNotNull(writer, "writer");
        if (value == null) {
            throw new NullPointerException("value == null");
        }
        writer.beginObject();
        for (Binding<T, Object> binding : this.bindings) {
            if (binding != null) {
                writer.name(binding.getName());
                binding.getAdapter().toJson(writer, (JsonWriter) binding.get(value));
            }
        }
        writer.endObject();
    }

    public String toString() {
        return "KotlinJsonAdapter(" + this.constructor.getReturnType() + ')';
    }

    /* compiled from: KotlinJsonAdapter.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\tHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u000bHÆ\u0003JQ\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\u0013\u0010\u001d\u001a\u00028\u00022\u0006\u0010\u001e\u001a\u00028\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010 \u001a\u00020!HÖ\u0001J\u001b\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00028\u0002¢\u0006\u0002\u0010%J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "K", "P", "", "name", "", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "property", "Lkotlin/reflect/KProperty1;", "parameter", "Lkotlin/reflect/KParameter;", "(Ljava/lang/String;Lcom/squareup/moshi/JsonAdapter;Lkotlin/reflect/KProperty1;Lkotlin/reflect/KParameter;)V", "getAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "getName", "()Ljava/lang/String;", "getParameter", "()Lkotlin/reflect/KParameter;", "getProperty", "()Lkotlin/reflect/KProperty1;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "get", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "", "set", "", "result", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "moshi-kotlin"}, k = 1, mv = {1, 1, 11})
    public static final /* data */ class Binding<K, P> {
        private final JsonAdapter<P> adapter;
        private final String name;
        private final KParameter parameter;
        private final KProperty1<K, P> property;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* bridge */ /* synthetic */ Binding copy$default(Binding binding, String str, JsonAdapter jsonAdapter, KProperty1 kProperty1, KParameter kParameter, int i, Object obj) {
            if ((i & 1) != 0) {
                str = binding.name;
            }
            if ((i & 2) != 0) {
                jsonAdapter = binding.adapter;
            }
            if ((i & 4) != 0) {
                kProperty1 = binding.property;
            }
            if ((i & 8) != 0) {
                kParameter = binding.parameter;
            }
            return binding.copy(str, jsonAdapter, kProperty1, kParameter);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final JsonAdapter<P> component2() {
            return this.adapter;
        }

        public final KProperty1<K, P> component3() {
            return this.property;
        }

        /* renamed from: component4, reason: from getter */
        public final KParameter getParameter() {
            return this.parameter;
        }

        public final Binding<K, P> copy(String name, JsonAdapter<P> adapter, KProperty1<K, ? extends P> property, KParameter parameter) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            Intrinsics.checkParameterIsNotNull(property, "property");
            return new Binding<>(name, adapter, property, parameter);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Binding)) {
                return false;
            }
            Binding binding = (Binding) other;
            return Intrinsics.areEqual(this.name, binding.name) && Intrinsics.areEqual(this.adapter, binding.adapter) && Intrinsics.areEqual(this.property, binding.property) && Intrinsics.areEqual(this.parameter, binding.parameter);
        }

        public int hashCode() {
            String str = this.name;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            JsonAdapter<P> jsonAdapter = this.adapter;
            int iHashCode2 = (iHashCode + (jsonAdapter != null ? jsonAdapter.hashCode() : 0)) * 31;
            KProperty1<K, P> kProperty1 = this.property;
            int iHashCode3 = (iHashCode2 + (kProperty1 != null ? kProperty1.hashCode() : 0)) * 31;
            KParameter kParameter = this.parameter;
            return iHashCode3 + (kParameter != null ? kParameter.hashCode() : 0);
        }

        public String toString() {
            return "Binding(name=" + this.name + ", adapter=" + this.adapter + ", property=" + this.property + ", parameter=" + this.parameter + ")";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Binding(String name, JsonAdapter<P> adapter, KProperty1<K, ? extends P> property, KParameter kParameter) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(adapter, "adapter");
            Intrinsics.checkParameterIsNotNull(property, "property");
            this.name = name;
            this.adapter = adapter;
            this.property = property;
            this.parameter = kParameter;
        }

        public final String getName() {
            return this.name;
        }

        public final JsonAdapter<P> getAdapter() {
            return this.adapter;
        }

        public final KProperty1<K, P> getProperty() {
            return this.property;
        }

        public final KParameter getParameter() {
            return this.parameter;
        }

        public final P get(K value) {
            return this.property.get(value);
        }

        public final void set(K result, P value) {
            if (value != KotlinJsonAdapterKt.ABSENT_VALUE) {
                KProperty1<K, P> kProperty1 = this.property;
                if (kProperty1 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KMutableProperty1<K, P>");
                }
                ((KMutableProperty1) kProperty1).set(result, value);
            }
        }
    }

    /* compiled from: KotlinJsonAdapter.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u0002H\u0096\u0002R(\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap;", "Lkotlin/collections/AbstractMap;", "Lkotlin/reflect/KParameter;", "", "parameterKeys", "", "parameterValues", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "getParameterKeys", "()Ljava/util/List;", "getParameterValues", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "containsKey", "", "key", "get", "moshi-kotlin"}, k = 1, mv = {1, 1, 11})
    public static final class IndexedParameterMap extends AbstractMap<KParameter, Object> {
        private final List<KParameter> parameterKeys;
        private final Object[] parameterValues;

        @Override // kotlin.collections.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof KParameter) {
                return containsKey((KParameter) obj);
            }
            return false;
        }

        @Override // kotlin.collections.AbstractMap, java.util.Map
        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof KParameter) {
                return get((KParameter) obj);
            }
            return null;
        }

        @Override // java.util.Map
        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return obj instanceof KParameter ? getOrDefault((KParameter) obj, obj2) : obj2;
        }

        public /* bridge */ Object getOrDefault(KParameter kParameter, Object obj) {
            return super.getOrDefault((Object) kParameter, (KParameter) obj);
        }

        public final List<KParameter> getParameterKeys() {
            return this.parameterKeys;
        }

        public final Object[] getParameterValues() {
            return this.parameterValues;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public IndexedParameterMap(List<? extends KParameter> parameterKeys, Object[] parameterValues) {
            Intrinsics.checkParameterIsNotNull(parameterKeys, "parameterKeys");
            Intrinsics.checkParameterIsNotNull(parameterValues, "parameterValues");
            this.parameterKeys = parameterKeys;
            this.parameterValues = parameterValues;
        }

        @Override // kotlin.collections.AbstractMap
        public Set<Map.Entry<KParameter, Object>> getEntries() {
            List<KParameter> list = this.parameterKeys;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                arrayList.add(new AbstractMap.SimpleEntry((KParameter) it.next(), this.parameterValues[i]));
                i++;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (T t : arrayList) {
                if (((AbstractMap.SimpleEntry) t).getValue() != KotlinJsonAdapterKt.ABSENT_VALUE) {
                    linkedHashSet.add(t);
                }
            }
            return linkedHashSet;
        }

        public boolean containsKey(KParameter key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return this.parameterValues[key.getIndex()] != KotlinJsonAdapterKt.ABSENT_VALUE;
        }

        public Object get(KParameter key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Object obj = this.parameterValues[key.getIndex()];
            if (obj != KotlinJsonAdapterKt.ABSENT_VALUE) {
                return obj;
            }
            return null;
        }
    }
}
