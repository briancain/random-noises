(ns random-noises.main
  (:require [overtone.live :as ovl]
            [random-noises.instruments :as inst]))

(defn chord-beat
  []
  (inst/minor-progression (inst/looper (ovl/metronome 120) inst/kick)))

(defn start-the-music
  "Start it"
  []
  (chord-beat))
