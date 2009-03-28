(import '(java.io FileInputStream FileOutputStream
		  PrintWriter)
	'(java.net Socket ServerSocket)
	'(javax.swing JFileChooser)
	'(java.io File))

(defn echo-func [sock]
  (let [out (new PrintWriter (.getOutputStream sock))
	in (new BufferedReader (.getInputStream sock))]
    (loop [text (.readLine in)]
      (if (not (.equals text "quit"))
	(do
	  (.println out (str "You said: " text))
	  (recur [text (.readLine in)]))))
    (.close out)
    (.close in)
    (.close sock)))

(defn serv [port func]
  (let [servSock (new ServerSocket port)]
    (loop []
      (.run (new Thread (func (.accept servSock))))
      (.println System/out "Spawned new thread")
      (recur))))
	