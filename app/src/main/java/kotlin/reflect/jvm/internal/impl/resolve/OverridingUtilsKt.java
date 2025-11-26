package kotlin.reflect.jvm.internal.impl.resolve;

import afu.org.checkerframework.checker.formatter.FormatUtil;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

/* compiled from: overridingUtils.kt */
/* loaded from: classes2.dex */
public final class OverridingUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <H> Collection<H> selectMostSpecificInEachOverridableGroup(Collection<? extends H> receiver, Function1<? super H, ? extends CallableDescriptor> descriptorByHandle) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(descriptorByHandle, "descriptorByHandle");
        if (receiver.size() <= 1) {
            return receiver;
        }
        LinkedList linkedList = new LinkedList(receiver);
        SmartSet smartSetCreate = SmartSet.Companion.create();
        while (true) {
            LinkedList linkedList2 = linkedList;
            if (!linkedList2.isEmpty()) {
                Object objFirst = CollectionsKt.first((List<? extends Object>) linkedList);
                final SmartSet smartSetCreate2 = SmartSet.Companion.create();
                Collection<FormatUtil> overridableGroup = OverridingUtil.extractMembersOverridableInBothWays(objFirst, linkedList2, descriptorByHandle, new Function1<H, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1
                    {
                        super(1);
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                        invoke2((OverridingUtilsKt$selectMostSpecificInEachOverridableGroup$overridableGroup$1<H>) obj);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(H it) {
                        SmartSet smartSet = smartSetCreate2;
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        smartSet.add(it);
                    }
                });
                if (overridableGroup.size() == 1 && smartSetCreate2.isEmpty()) {
                    Intrinsics.checkExpressionValueIsNotNull(overridableGroup, "overridableGroup");
                    Object objSingle = CollectionsKt.single(overridableGroup);
                    Intrinsics.checkExpressionValueIsNotNull(objSingle, "overridableGroup.single()");
                    smartSetCreate.add(objSingle);
                } else {
                    FormatUtil mostSpecific = (Object) OverridingUtil.selectMostSpecificMember(overridableGroup, descriptorByHandle);
                    Intrinsics.checkExpressionValueIsNotNull(mostSpecific, "mostSpecific");
                    CallableDescriptor callableDescriptorInvoke = descriptorByHandle.invoke(mostSpecific);
                    Intrinsics.checkExpressionValueIsNotNull(overridableGroup, "overridableGroup");
                    for (FormatUtil it : overridableGroup) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        if (!OverridingUtil.isMoreSpecific(callableDescriptorInvoke, descriptorByHandle.invoke(it))) {
                            smartSetCreate2.add(it);
                        }
                    }
                    SmartSet smartSet = smartSetCreate2;
                    if (!smartSet.isEmpty()) {
                        smartSetCreate.addAll(smartSet);
                    }
                    smartSetCreate.add(mostSpecific);
                }
            } else {
                return smartSetCreate;
            }
        }
    }

    public static final <D extends CallableDescriptor> void retainMostSpecificInEachOverridableGroup(Collection<D> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Collection<?> collectionSelectMostSpecificInEachOverridableGroup = selectMostSpecificInEachOverridableGroup(receiver, new Function1<D, D>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt$retainMostSpecificInEachOverridableGroup$newResult$1
            /* JADX WARN: Incorrect return type in method signature: (TD;)TD; */
            @Override // kotlin.jvm.functions.Function1
            public final CallableDescriptor invoke(CallableDescriptor receiver2) {
                Intrinsics.checkParameterIsNotNull(receiver2, "$receiver");
                return receiver2;
            }
        });
        if (receiver.size() == collectionSelectMostSpecificInEachOverridableGroup.size()) {
            return;
        }
        receiver.retainAll(collectionSelectMostSpecificInEachOverridableGroup);
    }
}
