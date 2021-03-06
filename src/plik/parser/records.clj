(ns plik.parser.records
  (:require [plik.parser.date]))

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defn transform-fields
  [fields]
  (update fields :date_of_birth plik.parser.date/read-date))

(defn make-person
  "Make a new Person record"
  [{:keys [last_name first_name gender date_of_birth favorite_color] :as fields}]
  (map->Person (transform-fields fields)))
