(ns plik.records-test
  (:require [clojure.test :refer :all]
            [plik.date]
            [plik.records :refer :all])
  (:import [plik.records Person]
           (org.joda.time DateTime)))

;; Directly initialize Person defrecord
(testing "Directly generate a Person record"
  (let [favorite-color "green"
        date-of-birth "7/6/1975"
        gender "F"
        first-name "Aneta"
        last-name "Hall"]

    (def aneta (Person. last-name first-name gender date-of-birth favorite-color))

    (testing "with valid attributes"
      (is (identical? (:last_name aneta) last-name))
      (is (identical? (get aneta :first_name) first-name))
      (is (identical? (get aneta :gender) gender))
      (is (identical? (get aneta :date_of_birth) date-of-birth))
      (is (identical? (get aneta :favorite_color) favorite-color)))))

;; Initialize Person record using constructor function
(testing "Construct a Person record"
  (let [favorite-color "blue"
        date-of-birth "12/19/1975"
        gender "M"
        first-name "Mike"
        last-name "Hall"]

    (def mike (plik.records/make-person {:favorite_color favorite-color
                                         :date_of_birth  date-of-birth
                                         :gender         gender
                                         :first_name     first-name
                                         :last_name      last-name}))

    (testing "with valid attributes"
      (is (identical? (get mike :last_name) last-name))
      (is (identical? (get mike :first_name) first-name))
      (is (identical? (get mike :gender) gender))
      (is (identical? (get mike :favorite_color) favorite-color))
      (is (instance? DateTime (get mike :date_of_birth)))
      (is (plik.date/date? (get mike :date_of_birth))))))
