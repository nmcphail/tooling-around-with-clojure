(ns hello-fig.erutils.core
  (:require [clojure.spec.alpha :as s]
            [clojure.pprint :as pp]))


(def erm

  {::entities [::user ::manager ]
   ::entity-definitions
   {::user {
      ::entity-name "User"
      ::entity-type :object
      ::entity-description "A user"
      ::entity-fields [::id ::first-name ::last-name ::age]
      ::entity-field-definitions {
       ::first-name {
             ::name "First Name"
             ::type :string
             ::length 10
             ::display true
             ::description "The first name of the user"}
       ::id {
             ::name "ID"
             ::type :integer
             ::display false
             ::description "The datbase ID"}
       ::last-name {
             ::name "Last Name"
             ::length 20
             ::display true
             ::description "Last Name"}}}

    }})



(s/def ::name string?)
(s/def ::length int?)
(s/def ::display boolean?)
(s/def ::description string?)
(s/def ::entity-field-definition (s/keys :req-un [::name ]
                                 :opt-un [::display ::description ::length]))


(s/def ::entity-name string? )
(s/def ::entity-type keyword? )
(s/def ::entity-description string?)
(s/def ::entity-fields vector?)
(s/def ::entity-field-definitions (s/map-of keyword? ::entity-field-definition ))
(s/def ::entity (s/keys :req-un [ ::entity-name
                              ::entity-type
                              ::entity-description
                              ::entity-fields
                              ::entity-field-definitions ]))



(s/def ::entity-definitions (s/map-of keyword? ::entity))
(s/def ::entities vector? )

(s/def ::erm (s/keys :req-un [::entities ::entity-definitions]))


(defn get-entities [erm]
  {:pre [(s/valid? ::erm erm)] }
  (get-in erm [:entities]))

(defn get-fields [erm entity]
  (get-in erm [:entity-definitions entity :entity-fields] ))

(defn get-field [erm entity field]
  (get-in erm [:entity-definitions entity :entity-field-definitions field] ))


(defn get-field-name [erm entity field]
  (get-in erm [:entity-definitions entity :entity-field-definitions field :name] ))


