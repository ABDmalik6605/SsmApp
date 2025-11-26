package com.micromerger.ssms.utils.biometric;

import android.media.AudioTrack;
import android.util.Log;

/* loaded from: classes2.dex */
public class PlaySound {
    private final int duration = 1;
    private final int sampleRate = 8000;
    private final int numSamples = 8000;
    private final double[] sample = new double[8000];
    private final double freqOfTone = 880.0d;
    private final byte[] generatedSnd = new byte[8000 * 2];

    public PlaySound() {
        for (int i = 0; i < this.numSamples; i++) {
            this.sample[i] = Math.sin((i * 6.283185307179586d) / 9.090909090909092d);
        }
        int length = this.sample.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            short s = (short) (r1[i3] * 32767.0d);
            byte[] bArr = this.generatedSnd;
            int i4 = i2 + 1;
            bArr[i2] = (byte) (s & 255);
            i2 = i4 + 1;
            bArr[i4] = (byte) ((s & 65280) >>> 8);
        }
    }

    public void playSound() throws IllegalStateException {
        AudioTrack audioTrack = new AudioTrack(3, 8000, 4, 2, this.numSamples, 0);
        byte[] bArr = this.generatedSnd;
        audioTrack.write(bArr, 0, bArr.length);
        try {
            audioTrack.play();
        } catch (IllegalStateException unused) {
            Log.e("AUDIO_PLAY", "playSound: Error Plating Audio");
        }
    }
}
