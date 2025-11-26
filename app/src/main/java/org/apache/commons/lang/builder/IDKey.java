package org.apache.commons.lang.builder;

/* loaded from: classes3.dex */
final class IDKey {

    /* renamed from: id, reason: collision with root package name */
    private final int f24id;
    private final Object value;

    public IDKey(Object obj) {
        this.f24id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f24id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        return this.f24id == iDKey.f24id && this.value == iDKey.value;
    }
}
