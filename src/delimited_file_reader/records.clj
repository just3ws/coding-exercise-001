(ns delimited-file-reader.records)

;; TODO: Look at reducing the repetion of arguments in defn and defrecord

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defrecord XPerson [last_name])

(def person-transformations { :last_name [clojure.string/reverse :last_name] })

(defn transform-person
  [xperson transformations]
  (into {} (map (fn [[k v]]
                  [k (apply (first v)
                            ((apply juxt (rest v)) xperson))]) transformations)))


(defn make-person
  "Make a new Person record"
  [{ :keys [last_name first_name gender date_of_birth favorite_color] :as fields }]
  (map->Person fields))

(defn make-xperson
  "Make a new Person record"
  [{ :keys [last_name] :as fields }]
  (map->XPerson fields))

(defn make-xperson
  "Make a new XPerson record"
  [{ :keys [last_name] :as fields }]
  (map->XPerson (transform-person { :last_name "Smith" } person-transformations)))



