(ns app.core
  (:require [rum.core :as rum]
            [scrum.dispatcher :as d]
            [scrum.router.core :as r]))

(def styles
  {:app {:display "flex"
         :flexDirection "column"
         :font "normal 16px sans-serif"}
   :header {:fontSize 20
            :color "#ffffff"
            :background "#000000"
            :textAlign "center"
            :padding "8px 0"}
   :layout {:display "flex"
            :flexDirection "column"
            :textAlign "center"
            :padding 8}})

(rum/defc Header [title]
  [:header {:style (:header styles)} title])

(rum/defc App < rum/reactive []
  (let [[handler params] (rum/react r/router)]
    [:div {:style (:app styles)}
     (Header "Title")
     [:div {:style (:layout styles)}
      (case handler
            :index [:div "INDEX" [:div [:a {:href "/items"} "Go to ITEMS"]]]
            :items [:div "ITEMS" [:ul (map-indexed
                                       (fn [idx item]
                                         [:li [:a {:href (str "/items/" (inc idx))} item]])
                                       ["Item #1" "Item #2" "Item #3"])]]
            :item [:div (str "ITEM #" (:id params))])]]))


(defonce dispatched-init (d/broadcast! :init))

(r/start!
 [["/" :index]
  ["/items" :items]
  ["/items/:id" :item]])

(rum/mount (App)
           (. js/document (getElementById "app")))
