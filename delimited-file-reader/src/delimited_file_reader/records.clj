(ns delimited-file-reader.records)

(defrecord Person [^String last_name
                   ^String first_name
                   gender
                   date_of_birth
                   ^String favorite_color])
