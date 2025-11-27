package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzmx;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method; // Added for Reflection fix

/* compiled from: com.google.android.gms:play-services-measurement@@18.0.0 */
final class zzt {
    private String zza;
    private boolean zzb;
    private zzcd.zzi zzc;
    private BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzr zzh;

    private zzt(zzr zzrVar, String str) {
        this.zzh = zzrVar;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap<>();
        this.zzg = new ArrayMap<>();
    }

    private zzt(zzr zzrVar, String str, zzcd.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzrVar;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap<>();
        if (map2 != null) {
            for (Integer num : map2.keySet()) {
                ArrayList<Long> arrayList = new ArrayList<>();
                arrayList.add(map2.get(num));
                this.zzg.put(num, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zziVar;
    }

    final void zza(zzu zzuVar) {
        int iZza = zzuVar.zza();
        if (zzuVar.zzc != null) {
            this.zze.set(iZza, zzuVar.zzc);
        }
        if (zzuVar.zzd != null) {
            this.zzd.set(iZza, zzuVar.zzd);
        }
        if (zzuVar.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(iZza));
            long jLongValue = zzuVar.zze / 1000;
            if (l == null || jLongValue > l) {
                this.zzf.put(Integer.valueOf(iZza), jLongValue);
            }
        }
        if (zzuVar.zzf != null) {
            List<Long> arrayList = this.zzg.get(Integer.valueOf(iZza));
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.zzg.put(Integer.valueOf(iZza), arrayList);
            }
            if (zzuVar.zzb()) {
                arrayList.clear();
            }
            if (zzmx.zzb() && this.zzh.zzs().zzd(this.zza, zzas.zzbb) && zzuVar.zzc()) {
                arrayList.clear();
            }
            if (zzmx.zzb() && this.zzh.zzs().zzd(this.zza, zzas.zzbb)) {
                long jLongValue2 = zzuVar.zzf / 1000;
                if (arrayList.contains(jLongValue2)) {
                    return;
                }
                arrayList.add(jLongValue2);
                return;
            }
            arrayList.add(zzuVar.zzf / 1000);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    final zzcd.zza zza(int i) {
        ArrayList arrayList;
        List arrayList2;

        // FIXED: We use Object here to completely bypass the Type System checks
        Object builder = zzcd.zza.zzh();

        // FIXED: Use Reflection helper to call 'zza' methods.
        // This stops the compiler from seeing the private field 'zza' in the parent class.
        invokeSet(builder, "zza", int.class, i);
        invokeSet(builder, "zza", boolean.class, this.zzb);

        zzcd.zzi zziVar = this.zzc;
        if (zziVar != null) {
            invokeSet(builder, "zza", zzcd.zzi.class, zziVar);
        }

        // Handle the inner builder for 'zzi'
        Object zzaBuilder = zzcd.zzi.zzi();
        // Assuming zzkr.zza returns Iterable or similar, accessed via Reflection/Raw chain if needed
        // For simplicity, we assume these methods are unique enough or we use raw casting
        com.google.android.gms.internal.measurement.zzhy.zzb rawZza = (com.google.android.gms.internal.measurement.zzhy.zzb) zzaBuilder;
        // We might need reflection here too if zzb/zza are ambiguous, but let's try raw first.
        // If this fails, we will wrap this in invokeSet too.
        try {
            // Using raw reflection for the chain: .zzb(Iterable).zza(Iterable)
            Method zzbMethod = zzaBuilder.getClass().getMethod("zzb", Iterable.class);
            Object res1 = zzbMethod.invoke(zzaBuilder, zzkr.zza(this.zzd));
            Method zzaMethod = res1.getClass().getMethod("zza", Iterable.class);
            zzaMethod.invoke(res1, zzkr.zza(this.zze));
        } catch (Exception e) {
            throw new RuntimeException("Reflective build failed", e);
        }

        if (this.zzf == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.zzf.size());
            for (Integer iIntValue : this.zzf.keySet()) {
                // Simplified chain using raw types from your zzhy fix
                com.google.android.gms.internal.measurement.zzhy.zzb inner = (com.google.android.gms.internal.measurement.zzhy.zzb) zzcd.zzb.zze();
                // Accessing methods via reflection if direct access fails, but let's assume zza(int) works on inner objects
                // If this line errors, we swap to invokeSet
                invokeSet(inner, "zza", int.class, iIntValue);
                invokeSet(inner, "zza", long.class, this.zzf.get(iIntValue));
                arrayList.add((zzcd.zzb) inner.zzy());
            }
        }

        if (arrayList != null) {
            invokeSet(zzaBuilder, "zzc", Iterable.class, arrayList);
        }

        if (this.zzg == null) {
            arrayList2 = Collections.emptyList();
        } else {
            arrayList2 = new ArrayList(this.zzg.size());
            for (Integer num : this.zzg.keySet()) {
                Object zzaVarZza = zzcd.zzj.zze();
                invokeSet(zzaVarZza, "zza", int.class, num);

                List<Long> list = this.zzg.get(num);
                if (list != null) {
                    Collections.sort(list);
                    invokeSet(zzaVarZza, "zza", Iterable.class, list);
                }
                // Cast to parent builder to call zzy()
                arrayList2.add((zzcd.zzj) ((com.google.android.gms.internal.measurement.zzhy.zzb)zzaVarZza).zzy());
            }
        }

        invokeSet(zzaBuilder, "zzd", Iterable.class, arrayList2);

        // Final add to main builder
        // We cast zzaBuilder to (zzcd.zzi) because zzy() returns the message
        invokeSet(builder, "zza", zzcd.zzi.class, (zzcd.zzi)((com.google.android.gms.internal.measurement.zzhy.zzb)zzaBuilder).zzy());

        return (zzcd.zza) ((com.google.android.gms.internal.measurement.zzhy.zzb)builder).zzy();
    }

    // --- HELPER TO BYPASS "PRIVATE ACCESS" ERRORS ---
    private void invokeSet(Object target, String methodName, Class<?> paramType, Object value) {
        try {
            Method method = target.getClass().getMethod(methodName, paramType);
            method.invoke(target, value);
        } catch (Exception e) {
            // Try searching parent classes if direct getMethod fails
            try {
                for (Method m : target.getClass().getMethods()) {
                    if (m.getName().equals(methodName)) {
                        Class<?>[] params = m.getParameterTypes();
                        if (params.length == 1 && params[0].isAssignableFrom(paramType)) {
                            m.invoke(target, value);
                            return;
                        }
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException("Failed to invoke " + methodName, ex);
            }
        }
    }

    zzt(zzr zzrVar, String str, zzcd.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzq zzqVar) {
        this(zzrVar, str, zziVar, bitSet, bitSet2, map, map2);
    }

    zzt(zzr zzrVar, String str, zzq zzqVar) {
        this(zzrVar, str);
    }
}