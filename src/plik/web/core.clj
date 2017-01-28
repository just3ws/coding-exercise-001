(ns plik.web.core
  (:require [clojure.java.io :as io]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [environ.core :refer [env]])
  (:gen-class))

(defroutes routes
  (GET "/" [] "Hello, world!")
  (route/not-found (slurp (io/resource "public/404.json"))))

(def application (wrap-defaults routes site-defaults))

(defn start
  [port]
  (ring/run-jetty application {:port port :join? false}))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (start port)))
