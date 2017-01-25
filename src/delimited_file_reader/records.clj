(ns delimited-file-reader.records
  (:require [delimited-file-reader.reader :as r]))

(defn pipe-delimited? [str] true)
(defn comma-delimited? [str] true)
(defn space-delimited? [str] true)

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defrecord XPerson [last_name date_of_birth])

(defn make-person
  "Make a new Person record"
  [{ :keys [last_name first_name gender date_of_birth favorite_color] :as fields }]
  (map->Person fields))

(defn make-xperson
  "Make a new XPerson record"
  [{ :keys [last_name date_of_birth] :as fields }]
  (map->XPerson (update fields :date_of_birth r/read-date)))



