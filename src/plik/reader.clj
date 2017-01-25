(ns delimited-file-reader.reader
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [semantic-csv.core :as sc]
            [camel-snake-kebab.core :refer :all]
            [delimited-file-reader.date :as d]
            [delimited-file-reader.sniffer :as sniffer]))

(defn open-file [path] (io/file (io/resource path)))

(defn transform-header [header]
  (->> header
       s/trim
       ->snake_case_keyword))

(defn cast-date-of-birth [dob]
  (when (d/date? dob) (d/read-date dob)))

(defn load-data [file]
  (let [deliminator (sniffer/infer-deliminator file)]
    (with-open [in-file (io/reader file)]
      (->>
       (csv/read-csv in-file :separator deliminator)
       (sc/remove-comments)
       (sc/mappify {:transform-header transform-header :keyify true})
       (sc/cast-with #(->> % str s/trim))
       (sc/cast-with {:date_of_birth cast-date-of-birth})
       doall))))

