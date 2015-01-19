(ns random-noises.instruments
  (:require [overtone.live :as ovl]))

(def kick (ovl/sample (ovl/freesound-path 2086)))

(ovl/definst saw-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (ovl/env-gen (ovl/env-lin attack sustain release) 1 1 0 1 ovl/FREE)
     (ovl/saw freq)
     vol))

(defn play-chord
  "Plays a chord"
  [chrd]
  (doseq [note chrd]
    (sawh note)))

(defn sawh
  "Takes a music note...like :C4"
  [music-note]
  (saw-wave (ovl/midi->hz (ovl/note music-note))))

(defn looper
  "Random drum beat that takes a metronome and a sound"
  [nome sound]
  (let [beat (nome)]
    (ovl/at (nome beat) (sound))
    (ovl/apply-by (nome (inc beat)) looper nome sound [])))

(defn minor-progression
  [nome]
  (let [time (ovl/now)]
    (ovl/at time (play-chord (ovl/chord :D3 :minor)))
    (ovl/at (+ 2000 time) (play-chord (ovl/chord :F3 :minor)))
    (ovl/at (+ 4000 time) (play-chord (ovl/chord :D3 :minor)))
    (ovl/at (+ 6000 time) (play-chord (ovl/chord :Eb3 :minor)))
    (ovl/apply-at (+ 7990 time) minor-progression nome [])))
