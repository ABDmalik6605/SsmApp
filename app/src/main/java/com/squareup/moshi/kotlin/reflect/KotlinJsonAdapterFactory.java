package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonClass;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.internal.Util;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.apache.commons.lang.ClassUtils;

/* compiled from: KotlinJsonAdapter.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory;", "Lcom/squareup/moshi/JsonAdapter$Factory;", "()V", "create", "Lcom/squareup/moshi/JsonAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "moshi", "Lcom/squareup/moshi/Moshi;", "moshi-kotlin"}, k = 1, mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class KotlinJsonAdapterFactory implements JsonAdapter.Factory {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9, types: [java.lang.Object] */
    @Override // com.squareup.moshi.JsonAdapter.Factory
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) throws SecurityException {
        String name;
        Object next;
        String name2;
        Json next2;
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(moshi, "moshi");
        Object obj = null;
        if (!annotations.isEmpty()) {
            return null;
        }
        Class<?> rawType = Types.getRawType(type);
        Intrinsics.checkExpressionValueIsNotNull(rawType, "rawType");
        if (rawType.isInterface() || rawType.isEnum() || !rawType.isAnnotationPresent(KotlinJsonAdapterKt.KOTLIN_METADATA) || Util.isPlatformType(rawType)) {
            return null;
        }
        JsonClass jsonClass = (JsonClass) rawType.getAnnotation(JsonClass.class);
        if (jsonClass != null && jsonClass.generateAdapter()) {
            return null;
        }
        if (rawType.isLocalClass()) {
            throw new IllegalArgumentException("Cannot serialize local class or object expression " + rawType.getName());
        }
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(rawType);
        if (kotlinClass.isAbstract()) {
            throw new IllegalArgumentException("Cannot serialize abstract class " + rawType.getName());
        }
        if (kotlinClass.isInner()) {
            throw new IllegalArgumentException("Cannot serialize inner class " + rawType.getName());
        }
        if (kotlinClass.getObjectInstance() != null) {
            throw new IllegalArgumentException("Cannot serialize object declaration " + rawType.getName());
        }
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(kotlinClass);
        if (primaryConstructor == null) {
            return null;
        }
        List<KParameter> parameters = primaryConstructor.getParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(parameters, 10)), 16));
        for (Object obj2 : parameters) {
            linkedHashMap.put(((KParameter) obj2).getName(), obj2);
        }
        boolean z = true;
        KCallablesJvm.setAccessible(primaryConstructor, true);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (KProperty1 kProperty1 : KClasses.getMemberProperties(kotlinClass)) {
            KParameter kParameter = (KParameter) linkedHashMap.get(kProperty1.getName());
            Field javaField = ReflectJvmMapping.getJavaField(kProperty1);
            if (Modifier.isTransient(javaField != null ? javaField.getModifiers() : 0)) {
                if (kParameter != null && !kParameter.isOptional()) {
                    throw new IllegalArgumentException("No default value for transient constructor " + kParameter);
                }
            } else {
                if (kParameter != null && (Intrinsics.areEqual(kParameter.getType(), kProperty1.getReturnType()) ^ z)) {
                    throw new IllegalArgumentException('\'' + kProperty1.getName() + "' has a constructor parameter of type " + kParameter.getType() + " but a property of type " + kProperty1.getReturnType() + ClassUtils.PACKAGE_SEPARATOR_CHAR);
                }
                if ((kProperty1 instanceof KMutableProperty1) || kParameter != null) {
                    KCallablesJvm.setAccessible(kProperty1, z);
                    List<Annotation> annotations2 = kProperty1.getAnnotations();
                    Iterator it = kProperty1.getAnnotations().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = obj;
                            break;
                        }
                        next = it.next();
                        if (((Annotation) next) instanceof Json) {
                            break;
                        }
                    }
                    Json json = (Json) next;
                    if (kParameter != null) {
                        annotations2 = CollectionsKt.plus((Collection) annotations2, (Iterable) kParameter.getAnnotations());
                        if (json == null) {
                            Iterator it2 = kParameter.getAnnotations().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    next2 = 0;
                                    break;
                                }
                                next2 = it2.next();
                                if (((Annotation) next2) instanceof Json) {
                                    break;
                                }
                            }
                            json = next2;
                        }
                    }
                    if (json == null || (name2 = json.name()) == null) {
                        name2 = kProperty1.getName();
                    }
                    Type typeResolve = Util.resolve(type, rawType, ReflectJvmMapping.getJavaType(kProperty1.getReturnType()));
                    List<Annotation> list = annotations2;
                    if (list == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    Object[] array = list.toArray(new Annotation[0]);
                    if (array != null) {
                        JsonAdapter adapter = moshi.adapter(typeResolve, Util.jsonAnnotations((Annotation[]) array), kProperty1.getName());
                        LinkedHashMap linkedHashMap3 = linkedHashMap2;
                        String name3 = kProperty1.getName();
                        Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                        if (kProperty1 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KProperty1<kotlin.Any, kotlin.Any?>");
                        }
                        linkedHashMap3.put(name3, new KotlinJsonAdapter.Binding(name2, adapter, kProperty1, kParameter));
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
            obj = null;
            z = true;
        }
        ArrayList arrayList = new ArrayList();
        for (KParameter kParameter2 : primaryConstructor.getParameters()) {
            KotlinJsonAdapter.Binding binding = (KotlinJsonAdapter.Binding) TypeIntrinsics.asMutableMap(linkedHashMap2).remove(kParameter2.getName());
            if (binding == null && !kParameter2.isOptional()) {
                throw new IllegalArgumentException("No property for required constructor " + kParameter2);
            }
            arrayList.add(binding);
        }
        Collection collectionValues = linkedHashMap2.values();
        Intrinsics.checkExpressionValueIsNotNull(collectionValues, "bindingsByName.values");
        CollectionsKt.addAll(arrayList, collectionValues);
        ArrayList<KotlinJsonAdapter.Binding> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (KotlinJsonAdapter.Binding binding2 : arrayList2) {
            if (binding2 == null || (name = binding2.getName()) == null) {
                name = "\u0000";
            }
            arrayList3.add(name);
        }
        Object[] array2 = arrayList3.toArray(new String[0]);
        if (array2 != null) {
            String[] strArr = (String[]) array2;
            JsonReader.Options options = JsonReader.Options.of((String[]) Arrays.copyOf(strArr, strArr.length));
            Intrinsics.checkExpressionValueIsNotNull(options, "options");
            return new KotlinJsonAdapter(primaryConstructor, arrayList, options).nullSafe();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
