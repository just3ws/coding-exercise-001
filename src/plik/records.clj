(ns delimited-file-reader.records
  (:require [delimited-file-reader.date :as d]))

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defn transform-fields [fields]
  (update fields :date_of_birth d/read-date))

(defn make-person
  "Make a new Person record"
  [{:keys [last_name first_name gender date_of_birth favorite_color] :as fields}]
  (map->Person (transform-fields fields)))
