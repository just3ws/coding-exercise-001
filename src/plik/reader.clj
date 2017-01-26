(ns plik.reader
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]
            [camel-snake-kebab.core :refer :all]
            [plik.date :as d]
            [plik.sniffer :as sniffer]))

(defn open-file
  [path]
  (io/file (io/resource path)))

(defn file-exists?
  [path]
  (.exists (clojure.java.io/as-file path)))

(defn transform-header
  [header]
  (->> header
       s/trim
       ->snake_case_keyword))

(defn cast-date-of-birth
  [dob]
  (when (d/date? dob) (d/read-date dob)))

(defn load-data
  [file]
  (let [deliminator (sniffer/infer-deliminator file)]
    (with-open [in-file (io/reader file)]
      (->>
       (csv/read-csv in-file :separator deliminator)
       (sc/remove-comments)
       (sc/mappify {:transform-header transform-header :keyify true})
       (sc/cast-with #(->> % str s/trim))
       (sc/cast-with {:date_of_birth cast-date-of-birth})
       doall))))

