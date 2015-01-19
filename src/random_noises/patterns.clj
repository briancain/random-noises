(ns random-noises.patterns
  (:require [overtone.live :as ovl]
            [random-noises.instruments :as inst]))

(defn play-arpeggio
  "doc-string" [chrd]
  (println "play"))

(defn play-chord
  "Plays a chord"
  [chrd]
  (doseq [note chrd]
    (sawh note)))

(defn looper
  "Drum beat that takes a metronome and a sound"
  [nome sound]
  (let [beat (nome)]
    (ovl/at (nome beat) (sound))
    (ovl/at (+ 2 beat) (inst/c-hat))
    (ovl/apply-by (nome (inc beat)) looper nome sound [])))

(defn minor-progression
  []
  (let [time (ovl/now)]
    (ovl/at time (play-chord (ovl/chord :D3 :minor)))
    (ovl/at (+ 2000 time) (play-chord (ovl/chord :F3 :minor)))
    (ovl/at (+ 4000 time) (play-chord (ovl/chord :D3 :minor)))
    (ovl/at (+ 6000 time) (play-chord (ovl/chord :Eb3 :minor)))
    (ovl/apply-at (+ 7990 time) minor-progression [])))
