package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;

/* compiled from: protoTypeTableUtil.kt */
/* loaded from: classes2.dex */
public final class ProtoTypeTableUtilKt {
    public static final List<ProtoBuf.Type> supertypes(ProtoBuf.Class receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> supertypeList = receiver.getSupertypeList();
        if (!(!supertypeList.isEmpty())) {
            supertypeList = null;
        }
        if (supertypeList != null) {
            return supertypeList;
        }
        List<Integer> supertypeIdList = receiver.getSupertypeIdList();
        Intrinsics.checkExpressionValueIsNotNull(supertypeIdList, "supertypeIdList");
        List<Integer> list = supertypeIdList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Integer it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(typeTable.get(it.intValue()));
        }
        return arrayList;
    }

    public static final ProtoBuf.Type type(ProtoBuf.Type.Argument receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasType()) {
            return receiver.getType();
        }
        if (receiver.hasTypeId()) {
            return typeTable.get(receiver.getTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type flexibleUpperBound(ProtoBuf.Type receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasFlexibleUpperBound()) {
            return receiver.getFlexibleUpperBound();
        }
        if (receiver.hasFlexibleUpperBoundId()) {
            return typeTable.get(receiver.getFlexibleUpperBoundId());
        }
        return null;
    }

    public static final List<ProtoBuf.Type> upperBounds(ProtoBuf.TypeParameter receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<ProtoBuf.Type> upperBoundList = receiver.getUpperBoundList();
        if (!(!upperBoundList.isEmpty())) {
            upperBoundList = null;
        }
        if (upperBoundList != null) {
            return upperBoundList;
        }
        List<Integer> upperBoundIdList = receiver.getUpperBoundIdList();
        Intrinsics.checkExpressionValueIsNotNull(upperBoundIdList, "upperBoundIdList");
        List<Integer> list = upperBoundIdList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Integer it : list) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            arrayList.add(typeTable.get(it.intValue()));
        }
        return arrayList;
    }

    public static final ProtoBuf.Type returnType(ProtoBuf.Function receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasReturnType()) {
            ProtoBuf.Type returnType = receiver.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType");
            return returnType;
        }
        if (receiver.hasReturnTypeId()) {
            return typeTable.get(receiver.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
    }

    public static final boolean hasReceiver(ProtoBuf.Function receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.hasReceiverType() || receiver.hasReceiverTypeId();
    }

    public static final ProtoBuf.Type receiverType(ProtoBuf.Function receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasReceiverType()) {
            return receiver.getReceiverType();
        }
        if (receiver.hasReceiverTypeId()) {
            return typeTable.get(receiver.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type returnType(ProtoBuf.Property receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasReturnType()) {
            ProtoBuf.Type returnType = receiver.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType");
            return returnType;
        }
        if (receiver.hasReturnTypeId()) {
            return typeTable.get(receiver.getReturnTypeId());
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
    }

    public static final boolean hasReceiver(ProtoBuf.Property receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.hasReceiverType() || receiver.hasReceiverTypeId();
    }

    public static final ProtoBuf.Type receiverType(ProtoBuf.Property receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasReceiverType()) {
            return receiver.getReceiverType();
        }
        if (receiver.hasReceiverTypeId()) {
            return typeTable.get(receiver.getReceiverTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type type(ProtoBuf.ValueParameter receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasType()) {
            ProtoBuf.Type type = receiver.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "type");
            return type;
        }
        if (receiver.hasTypeId()) {
            return typeTable.get(receiver.getTypeId());
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
    }

    public static final ProtoBuf.Type varargElementType(ProtoBuf.ValueParameter receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasVarargElementType()) {
            return receiver.getVarargElementType();
        }
        if (receiver.hasVarargElementTypeId()) {
            return typeTable.get(receiver.getVarargElementTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type outerType(ProtoBuf.Type receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasOuterType()) {
            return receiver.getOuterType();
        }
        if (receiver.hasOuterTypeId()) {
            return typeTable.get(receiver.getOuterTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type abbreviatedType(ProtoBuf.Type receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasAbbreviatedType()) {
            return receiver.getAbbreviatedType();
        }
        if (receiver.hasAbbreviatedTypeId()) {
            return typeTable.get(receiver.getAbbreviatedTypeId());
        }
        return null;
    }

    public static final ProtoBuf.Type underlyingType(ProtoBuf.TypeAlias receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasUnderlyingType()) {
            ProtoBuf.Type underlyingType = receiver.getUnderlyingType();
            Intrinsics.checkExpressionValueIsNotNull(underlyingType, "underlyingType");
            return underlyingType;
        }
        if (receiver.hasUnderlyingTypeId()) {
            return typeTable.get(receiver.getUnderlyingTypeId());
        }
        throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
    }

    public static final ProtoBuf.Type expandedType(ProtoBuf.TypeAlias receiver, TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (receiver.hasExpandedType()) {
            ProtoBuf.Type expandedType = receiver.getExpandedType();
            Intrinsics.checkExpressionValueIsNotNull(expandedType, "expandedType");
            return expandedType;
        }
        if (receiver.hasExpandedTypeId()) {
            return typeTable.get(receiver.getExpandedTypeId());
        }
        throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
    }
}
