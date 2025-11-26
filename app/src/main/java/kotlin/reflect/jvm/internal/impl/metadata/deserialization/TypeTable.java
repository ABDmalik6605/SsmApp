package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;

/* compiled from: TypeTable.kt */
/* loaded from: classes2.dex */
public final class TypeTable {
    private final List<ProtoBuf.Type> types;

    public TypeTable(ProtoBuf.TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        ArrayList originalTypes = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List<ProtoBuf.Type> typeList = typeTable.getTypeList();
            Intrinsics.checkExpressionValueIsNotNull(typeList, "typeTable.typeList");
            List<ProtoBuf.Type> list = typeList;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (ProtoBuf.Type typeBuild : list) {
                int i2 = i + 1;
                if (i >= firstNullable) {
                    typeBuild = typeBuild.toBuilder().setNullable(true).build();
                }
                arrayList.add(typeBuild);
                i = i2;
            }
            originalTypes = arrayList;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(originalTypes, "originalTypes");
        }
        this.types = originalTypes;
    }

    public final ProtoBuf.Type get(int i) {
        return this.types.get(i);
    }
}
