(ns console-logger-api.core
  (:require [cljs.nodejs :as nodejs]))

(defn start []
  (let [api (nodejs/require "./api")
        styles (.-styles (.-inspect (nodejs/require "util")))
        colorsTrue (js-obj "colors" true)
        setColor (fn [color]
          (aset styles "string" color))
        log (fn [text]
          (.dir js/console text colorsTrue))
        error (fn [text]
          (setColor "red")
          (log text))
        warning (fn [text]
          (setColor "yellow")
          (log text))
        message (fn [text]
          (setColor "green")
          (log text))]

    (aset api "error" error)
    (aset api "warning" warning)
    (aset api "message" message)))

(set! *main-cli-fn* start)
