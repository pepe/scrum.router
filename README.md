# scrum.router

*Routing library for [Scrum](https://github.com/roman01la/scrum)*

## Table of Contents

- [Installation](#installation)
- [How it works](#how-it-works)
- [Usage](#usage)
- [License](#license)

## Installation

Add to project.clj: `[org.roman01la/scrum.router "0.1.0-SNAPSHOT"]`

## How it works

`scrum.router` is a simple and minimal routing library for [Scrum](https://github.com/roman01la/scrum) which is just [bide](https://github.com/funcool/bide) and [pushy](https://github.com/kibu-australia/pushy) hooked together into Scrum’s architecture.

`scrum.router` namespace provides two functions `start!` and `stop!` to control router lifecycle, and `router` atom which is Scrum’s subscription. The subscription holds a value which is a vector of two values: `handler` and `params`.

## Usage

```clojure
(ns app.core
  (:require [rum.core :as rum]
            [scrum.dispatcher :as d]
            [scrum.router :as r]))

(rum/defc App < rum/reactive []
  ;; subscribe to router's controller
  (let [[handler params] (rum/react r/router)]
    (case handler
      :index (render-index)
      :users (render-users)
      :user (render-user (:id params))
      nil)))

;; init Scrum
(defonce dispatched-init (d/broadcast! :init))

;; define routes in Bide format
(def routes
  [["/" :index]
   ["/users" :users]
   ["/users/:id" :user]])

;; init router
(r/start! routes)

(rum/mount (App)
           (. js/document (getElementById "app")))
```

## License

Copyright © 2017 Roman Liutikov

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
