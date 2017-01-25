(ns delimited-file-reader.records
  (:require [clj-time.format :as f]))

(def date-format (f/formatter "M/d/yyyy"))
(defn read-date [str] (f/parse date-format str))
(defn write-date [date] (f/unparse date-format date))
(defn date? [str] (instance? org.joda.time.DateTime (try (read-date str)
                                                         (catch java.lang.IllegalArgumentException e))))

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defrecord XPerson [last_name date_of_birth])

(def person-transformations {
                             :date_of_birth [read-date :date_of_birth]
                             })

(defn transform-person
  [xperson transformations]
  (into {} (map (fn [[k v]]
                  [k (apply (first v)
                            ((apply juxt (rest v)) xperson))]) transformations)))


(defn make-person
  "Make a new Person record"
  [{ :keys [last_name first_name gender date_of_birth favorite_color] :as fields }]
  (map->Person fields))

;(defn make-xperson
  ;"Make a new Person record"
  ;[{ :keys [last_name date_of_birth] :as fields }]
  ;(map->XPerson fields))

(defn make-xperson
  "Make a new XPerson record"
  [{ :keys [last_name date_of_birth] :as fields }]
  (map->XPerson (transform-person { :last_name "Smith" :date_of_birth "7/6/1975" } person-transformations)))



