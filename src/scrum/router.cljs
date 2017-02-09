(ns scrum.router
  (:require [rum.core :as rum]
            [bide.core :as r]
            [pushy.core :as p]
            [scrum.dispatcher :as d]
            [scrum.core :refer [subscription]]
            [scrum.router.controller :refer [control]]))

(d/register! :router control)

(def router (subscription [:router :route]))

(defn- set-page!
  "Dispatch handler and route params to the app state"
  [[handler params]]
  (d/dispatch! :router :push handler params))

(defn start!
  "Initialize router"
  [routes]
  (let [history (p/pushy set-page! (partial r/match (r/router routes)))]
    (p/start! history)
    history))

(defn stop!
  "Stop router"
  [history]
  (p/stop! history))
