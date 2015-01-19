(ns random-noises.main
  (:require [overtone.live :as ovl]
            [random-noises.patterns :as pattern]
            [random-noises.instruments :as inst]))

(defn chord-beat
  []
  (pattern/looper (ovl/metronome 120) inst/adv-kick)
  (pattern/minor-progression inst/squarew))

(defn start-the-music
  "Start it"
  []
  (chord-beat))
