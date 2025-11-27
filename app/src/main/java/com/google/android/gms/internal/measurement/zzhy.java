package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@18.0.0 */
@SuppressWarnings({"unchecked", "rawtypes", "MissingOverride"})
public abstract class zzhy<MessageType extends zzhy<MessageType, BuilderType>, BuilderType extends zzhy.zzb<MessageType, BuilderType>> extends zzgg<MessageType, BuilderType> {

    private static Map<Object, zzhy<?, ?>> zzd = new ConcurrentHashMap();
    protected zzks zzb = zzks.zza();
    private int zzc = -1;

    public static abstract class zza<T extends zzhy<T, ?>> extends zzgk<T> {
        protected final T zzaInstance;
        public zza(T t) { this.zzaInstance = t; }
    }

    static final class zzc implements zzht<zzc> {
        @Override public final int zza() { return 0; }
        @Override public final zzlg zzb() { return null; }
        @Override public final zzln zzc() { return null; }
        @Override public final boolean zzd() { return false; }
        @Override public final boolean zze() { return false; }
        @Override public final zzji zza(zzji zzjiVar, zzjj zzjjVar) { return null; }
        @Override public final zzjo zza(zzjo zzjoVar, zzjo zzjoVar2) { return null; }
        @Override public final int compareTo(zzc other) { return 0; }
    }

    public static final class zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
    }

    public static class zzf<ContainingType extends zzjj, Type> extends zzhm<ContainingType, Type> {
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzhy.zzb<MessageType, BuilderType>> extends zzhy<MessageType, BuilderType> implements zzjl {
        protected zzhr<zzc> zzc = zzhr.zza();
        final zzhr<zzc> zza() throws CloneNotSupportedException {
            if (this.zzc.zzc()) { this.zzc = (zzhr) this.zzc.clone(); }
            return this.zzc;
        }
    }

    public String toString() {
        return zzjk.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) { return this.zza; }
        this.zza = zzjx.zza().zza((Object)this).zza(this);
        return this.zza;
    }

    // --- FIXED SECTION START ---
    // Changed Generics to be less strict to stop "Bound" errors
    public static abstract class zzb<MessageType extends zzhy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgi<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc; // Renamed to avoid confusion with parent

        protected zzb(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) messagetype.zza(zze.zzd, null, null);
        }

        protected void zzu() {
            MessageType messagetype = (MessageType) this.zza.zza(zze.zzd, null, null);
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override
        public final boolean zzbn() {
            return zzhy.zza(this.zza, false);
        }

        @Override
        public MessageType zzx() {
            if (this.zzb) { return this.zza; }
            MessageType messagetype = this.zza;
            zzjx.zza().zza((Object)messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        @Override
        public final MessageType zzy() {
            MessageType messagetype = (MessageType) zzx();
            if (messagetype.zzbn()) { return messagetype; }
            throw new zzkq(messagetype);
        }

        @Override
        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) { zzu(); this.zzb = false; }
            zza(this.zza, messagetype);
            return (BuilderType) this;
        }

        private static void zza(Object messagetype, Object messagetype2) {
            zzjx.zza().zza((Object)messagetype).zzb(messagetype, messagetype2);
        }

        // FIXED: Return 'zzgi' to match parent class signature exactly
        @Override
        public final BuilderType zza(byte[] bArr, int i, int i2, zzhl zzhlVar) throws zzij {
            if (this.zzb) { zzu(); this.zzb = false; }
            try {
                zzjx.zza().zza((Object)this.zza).zza(this.zza, bArr, 0, i2, new zzgo(zzhlVar));
                return (BuilderType) this;
            } catch (zzij e) { throw e; }
            catch (IOException e2) { throw new RuntimeException("Reading from byte array should not throw IOException.", e2); }
            catch (IndexOutOfBoundsException unused) { throw zzij.zza(); }
        }

        @Override
        public final BuilderType zza(zzhb zzhbVar, zzhl zzhlVar) throws IOException {
            if (this.zzb) { zzu(); this.zzb = false; }
            try {
                zzjx.zza().zza((Object)this.zza).zza(this.zza, zzhg.zza(zzhbVar), zzhlVar);
                return (BuilderType) this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) { throw ((IOException) e.getCause()); }
                throw e;
            }
        }

        // FIXED: Return 'zzgi' to match parent class signature exactly
        @Override
        public final BuilderType zza(byte[] bArr, int i, int i2) throws zzij {
            return zza(bArr, 0, i2, zzhl.zza());
        }

        // FIXED: Manual clone to avoid CloneNotSupportedException
        @Override
        public final BuilderType clone() {
            BuilderType builder = (BuilderType) this.zzc.zza(zze.zze, null, null);
            builder.zza((MessageType) zzx());
            return builder;
        }

        @Override
        public final /* synthetic */ zzjj zzbv() {
            return this.zzc;
        }
    }
    // --- FIXED SECTION END ---

    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj != null && getClass() == obj.getClass()) {
            return zzjx.zza().zza((Object)this).zza(this, (zzhy) obj);
        }
        return false;
    }

    protected final <MessageType extends zzhy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzbm() {
        return (BuilderType) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override
    public final boolean zzbn() {
        return zza((zzhy)this, true);
    }

    public final BuilderType zzbo() {
        BuilderType buildertype = (BuilderType) zza(zze.zze, (Object) null, (Object) null);
        buildertype.zza(this);
        return buildertype;
    }

    @Override
    final int zzbl() { return this.zzc; }

    @Override
    final void zzc(int i) { this.zzc = i; }

    @Override
    public final void zza(zzhi zzhiVar) throws IOException {
        zzjx.zza().zza((Object)this).zza(this, (zzlm) zzhk.zza(zzhiVar));
    }

    @Override
    public final int zzbp() {
        if (this.zzc == -1) {
            this.zzc = zzjx.zza().zza((Object)this).zzb(this);
        }
        return this.zzc;
    }

    static <T extends zzhy<?, ?>> T zza(Class<T> cls) {
        zzhy<?, ?> zzhyVar = zzd.get(cls);
        if (zzhyVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzhyVar = zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzhyVar == null) {
            zzhyVar = (T) ((zzhy) zzkz.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (zzhyVar == null) { throw new IllegalStateException(); }
            zzd.put(cls, zzhyVar);
        }
        return (T) zzhyVar;
    }

    protected static <T extends zzhy<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    protected static Object zza(zzjj zzjjVar, String str, Object[] objArr) {
        return new zzjz(zzjjVar, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static final <T extends zzhy<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zze.zza, null, null)).byteValue();
        if (bByteValue == 1) { return true; }
        if (bByteValue == 0) { return false; }
        boolean zZzd = zzjx.zza().zza((Object)t).zzd(t);
        if (z) {
            t.zza(zze.zzb, zZzd ? t : null, null);
        }
        return zZzd;
    }

    protected static zzie zzbq() { return zzib.zzd(); }
    protected static zzih zzbr() { return zzix.zzd(); }

    protected static zzih zza(zzih zzihVar) {
        int size = zzihVar.size();
        return (zzih) zzihVar.zza(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzig<E> zzbs() { return zzjw.zzd(); }

    protected static <E> zzig<E> zza(zzig<E> zzigVar) {
        int size = zzigVar.size();
        return zzigVar.zza(size == 0 ? 10 : size << 1);
    }

    @Override
    public final /* synthetic */ zzji zzbt() {
        zzb zzbVar = (zzb) zza(zze.zze, (Object) null, (Object) null);
        zzbVar.zza((MessageType) this);
        return zzbVar;
    }

    @Override
    public final /* synthetic */ zzji zzbu() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override
    public final /* synthetic */ zzjj zzbv() {
        return (zzhy) zza(zze.zzf, (Object) null, (Object) null);
    }
}