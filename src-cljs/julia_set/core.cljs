(ns julia-set.core)

(enable-console-print!)

(println "Hello, world!")

(defn make-point [i width height real-max im-max]
  (let [dx (/ (* 2 real-max) width )
        dy (/ (* 2 im-max) height)]
    [(+ (- real-max) (* dx (mod i height)))
     (- im-max (* dy (int (/ i width))))]))

(defn sum [[zr zi] [cr ci]]
  "Sum of two complex numbers."
  [(+ zr cr) (+ zi ci)])

(defn pow2 [[zr zi]]
  "Raise given complex number to the power o two."
  [(- (* zr zr) (* zi zi)) (* 2 zr zi)])

(defn module [[zr zi]]
  "Get the module of complex number."
  (Math/sqrt (+ (* zr zr) (* zi zi))))

(defn x [z c] (sum (pow2 z) c))

(defn promote-maybe [[z n] c]
  (let [an (x z c)]
    (if (< (module an) 2)
      [an (inc n)]
      [z n])))

(defonce canvas (. js/document (getElementById "canvas")))
(defonce ctx (.getContext canvas "2d"))
(defonce id (.createImageData ctx 1 1))
(aset (.-data id) 3 255)

(set! (.-fillStyle ctx) "#000")

(def width 600)
(def height 600)

(defn draw! [ps i max-iter]
  (loop [ps ps
         k 0]
    (when-let [[z n] (first ps)]
      (when (< n i)
        ;; (set! (.-fillStyle ctx)
        ;;       (str "#" (.toString (* n (int (/ 255 max-iter))) 16) "0000"))
        ;; (.fillRect ctx (mod k height) (int (/ k width)) 1 1)
        (aset (.-data id) 0 (* n (int (/ 256 max-iter))))
        (.putImageData ctx id (mod k height) (int (/ k width)))
        )
      (recur (rest ps) (inc k)))))

(defn clear! [width height]
  (.clearRect ctx 0 0 width height))

(set! (.-width canvas) width)
(set! (.-height canvas) height)

(def real-max 1.5)
(def im-max 1.25)
(def max-iter 24)
(def c [0.123 0.745])

(defn build [c widht height real-max im-max max-iter]
  (loop [i 0
         ps (map #(vector (make-point % width height real-max im-max) 0)
                 (range (* width height)))]
    (if (< i max-iter)
      (do
        (recur (inc i) (map #(promote-maybe % c) ps)))
      ps)))

(defn render! [ps max-iter]
  (println "Start: " (js/Date.))
  (draw! ps (- max-iter 1) max-iter)
  (println "Stop: " (js/Date.)))

(defonce fr (atom (build c width height real-max im-max max-iter)))

(render! @fr max-iter)
