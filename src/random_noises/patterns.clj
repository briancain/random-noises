(ns random-noises.patterns
  (:require [overtone.live :as ovl]
            [random-noises.instruments :as inst]))

(defn play-arpeggio
  "doc-string"
  [chrd instrument]
  (let [time (ovl/now)]
    (ovl/at time (instrument (first chrd)))
    (ovl/at (+ 1000 time) (instrument (last chrd)))
    (ovl/at (+ 2000 time) (instrument (second chrd)))))

(defn play-chord
  "Plays a chord"
  [chrd instrument]
  (doseq [note chrd]
    (instrument note)))

(defn looper
  "Drum beat that takes a metronome and a sound"
  [nome sound]
  (let [beat (nome)]
    (ovl/at (nome beat) (sound))
    (ovl/at (+ 2 beat) (inst/c-hat))
    (ovl/apply-by (nome (inc beat)) looper nome sound [])))

(defn minor-progression
  [instrument]
  (let [time (ovl/now)]
    (ovl/at time (play-chord (ovl/chord :D3 :minor) instrument))
    (ovl/at (+ 2000 time) (play-chord (ovl/chord :F3 :minor) instrument))
    (ovl/at (+ 4000 time) (play-chord (ovl/chord :D3 :minor) instrument))
    (ovl/at (+ 6000 time) (play-chord (ovl/chord :Eb3 :minor) instrument))
    (ovl/apply-at (+ 7990 time) minor-progression instrument [])))
