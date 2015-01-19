(ns random-noises.main
  (:require [overtone.live :as ovl]
            [random-noises.instruments :as inst]))

(defn chord-beat
  []
  (inst/looper (ovl/metronome 120) inst/adv-kick)
  (inst/minor-progression))

(defn start-the-music
  "Start it"
  []
  (chord-beat))
