package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* loaded from: classes2.dex */
public class TransientReceiver extends AbstractReceiverValue {
    public TransientReceiver(KotlinType kotlinType) {
        this(kotlinType, null);
    }

    private TransientReceiver(KotlinType kotlinType, ReceiverValue receiverValue) {
        super(kotlinType, receiverValue);
    }

    public String toString() {
        return "{Transient} : " + getType();
    }
}
