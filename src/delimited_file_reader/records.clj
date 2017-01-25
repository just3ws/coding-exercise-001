(ns delimited-file-reader.records
  (:require [delimited-file-reader.reader :as r]))

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defn make-person
  "Make a new Person record"
  [{ :keys [last_name first_name gender date_of_birth favorite_color] :as fields }]
  (map->Person (update fields :date_of_birth r/read-date)))
