(ns delimited-file-reader.records)

(def fields (vector :last_name :first_name :gender :date_of_birth :favorite_color))

(defrecord Person [last_name first_name gender date_of_birth favorite_color])

(defn make-person
  "Make a new Person record"
  ([last_name first_name gender date_of_birth favorite_color]
   (->Person last_name first_name gender date_of_birth favorite_color))
  ([{:keys [last_name first_name gender date_of_birth favorite_color]}]
   (make-person last_name first_name gender date_of_birth favorite_color)))
