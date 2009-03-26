(defn junk []
  (println "a")
  (Thread/sleep 3000)
  (println "b"))

(defn main []
  (let [t (new Thread junk)
	h (new Thread junk)]
    (println "in main")
    (.start t)
    (.start h)
    (println "end main")
    (.join t)
    (.join h)))