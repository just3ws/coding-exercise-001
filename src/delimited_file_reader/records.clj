(ns delimited-file-reader.records)

;; TODO: Look at reducing the repetion of arguments in defn and defrecord

(defrecord Person [last_name
                   first_name
                   gender
                   date_of_birth
                   favorite_color])

(defn make-person
  "Make a new Person record"
  ([last_name first_name gender date_of_birth favorite_color]
   (->Person last_name first_name gender date_of_birth favorite_color))
  ([{ :keys [last_name first_name gender date_of_birth favorite_color] :as fields }]
   (map->Person fields)))


