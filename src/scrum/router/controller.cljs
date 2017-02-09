(ns scrum.router.controller)

(def initial-state
  {:route nil})

(defmulti control (fn [action] action))

(defmethod control :default [action _ db]
  db)

(defmethod control :init [_ _ db]
  (assoc db :router initial-state))

(defmethod control :push [_ [route params] db]
  (assoc-in db [:router :route] [route params]))
