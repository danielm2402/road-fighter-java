/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import static control.Globales.CAR_CRASH_SOUND;
import static control.Globales.CAR_DRIFT_SOUND;
import static control.Globales.CAR_SOUND;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Sounds {

    private final File car_sound = new File(CAR_SOUND);
    private final File car_crash_sound = new File(CAR_CRASH_SOUND);
    private final File car_drift_sound = new File(CAR_DRIFT_SOUND);
    private Clip car_sound_clip;
    private Clip car_crash_clip;
    private Clip car_drift_clip;

    private boolean isCarSoundActive = false;
    private boolean isCarCrashSoundActive = false;
    private boolean isCarDriftSoundActive = false;
    private double volumeToIncrease = 0.01;
    private double volume = 0.0;

    public Sounds() {

    }

    public double getVolumeToIncrease() {
        return volumeToIncrease;
    }

    public void setVolumeToIncrease(double volumeToIncrease) {
        this.volumeToIncrease = volumeToIncrease;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void playCarSound() {
        try {
            if (!isCarSoundActive) {
                car_sound_clip = AudioSystem.getClip();
                car_sound_clip.open(AudioSystem.getAudioInputStream(this.car_sound));
                FloatControl gainControl = (FloatControl) car_sound_clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(0));
                car_sound_clip.start();
                this.isCarSoundActive = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en audio:\n" + e);
        }
    }

    public void stopCarSound() {
        if (this.isCarSoundActive) {
            car_sound_clip.stop();
            this.isCarSoundActive = false;
        }

    }

    public void playCarCrashSound() {
        try {
            if (!isCarCrashSoundActive) {
                car_crash_clip = AudioSystem.getClip();
                car_crash_clip.open(AudioSystem.getAudioInputStream(this.car_crash_sound));
                FloatControl gainControl = (FloatControl) car_crash_clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(0.3));
                car_crash_clip.start();
                this.isCarCrashSoundActive = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en audio:\n" + e);
        }
    }

    public void stopCarCrashSound() {
        if (this.isCarCrashSoundActive) {
            car_crash_clip.stop();
            this.isCarCrashSoundActive = false;
        }

    }

    public void playCarDriftSound() {
        try {
            if (!isCarDriftSoundActive) {
                car_drift_clip = AudioSystem.getClip();
                car_drift_clip.open(AudioSystem.getAudioInputStream(this.car_drift_sound));
                FloatControl gainControl = (FloatControl) car_drift_clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(0.3));
                car_drift_clip.start();
                this.isCarDriftSoundActive = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en audio:\n" + e);
        }

    }

    public void stopCarDriftSound() {
        if (this.isCarDriftSoundActive) {
            car_drift_clip.stop();
            this.isCarDriftSoundActive = false;
        }

    }

    public void stopSounds() {
        car_sound_clip.stop();
        car_drift_clip.stop();
        car_drift_clip.stop();
    }

    public void setGeneralVolume(float volume) {
        if (this.car_sound_clip != null) {
            if (volume > 0f && volume < 1f) {
                this.volume = volume;
                FloatControl gainControl = (FloatControl) this.car_sound_clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(20f * (float) Math.log10(volume));
            }

        }
    }
}
