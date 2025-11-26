package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;

/* loaded from: classes2.dex */
public class IntersectionTypeConstructor implements TypeConstructor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final int hashCode;
    private final Set<KotlinType> intersectedTypes;

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    /* renamed from: getDeclarationDescriptor */
    public ClassifierDescriptor mo1333getDeclarationDescriptor() {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public IntersectionTypeConstructor(Collection<KotlinType> collection) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(collection);
        this.intersectedTypes = linkedHashSet;
        this.hashCode = linkedHashSet.hashCode();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List<TypeParameterDescriptor> getParameters() {
        return Collections.emptyList();
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection<KotlinType> getSupertypes() {
        return this.intersectedTypes;
    }

    public MemberScope createScopeForKotlinType() {
        return TypeIntersectionScope.create("member scope for intersection type " + this, this.intersectedTypes);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        return this.intersectedTypes.iterator().next().getConstructor().getBuiltIns();
    }

    public String toString() {
        return makeDebugNameForIntersectionType(this.intersectedTypes);
    }

    private static String makeDebugNameForIntersectionType(Iterable<KotlinType> iterable) {
        StringBuilder sb = new StringBuilder("{");
        Iterator<KotlinType> it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(" & ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Set<KotlinType> set = this.intersectedTypes;
        Set<KotlinType> set2 = ((IntersectionTypeConstructor) obj).intersectedTypes;
        return set == null ? set2 == null : set.equals(set2);
    }

    public int hashCode() {
        return this.hashCode;
    }
}
