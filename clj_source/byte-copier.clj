(import '(java.io FileInputStream FileOutputStream)
	'(java.net Socket ServerSocket)
	'(javax.swing JFileChooser)
	'(java.io File))


(defn copy [from to]
  (let [inStream  (new FileInputStream from)
	outStream (new FileOutputStream to)]
    (loop [c (.read inStream)]
      (if (not (= -1 c))
	(do 
	  (.write outStream c)
	  (recur (.read inStream)))))
    (.close inStream)
    (.close outStream)))


(defn send-to
  ([file port] (send-to file port "127.0.0.1"))
  ([file port addr]
     (let [sock (new Socket addr port)
	   out (.getOutputStream sock)
	   in  (new FileInputStream file)]
       (loop [c (.read in)]
	 (if (not (= -1 c))
	   (do
	     (.write out c)
	     (recur (.read in)))))
       (.close sock)
       (.close out)
       (.close in))))


(defn get-file []
  (let [f (doto (new JFileChooser)
	    (.setCurrentDirectory (new File "."))
	    (.showOpenDialog nil))]
    (send-to (.getSelectedFile f) 31023)))


(defn recv-from 
  ([file] (recv-from file 31023))
  ([file port] 
     (let [servSock (new ServerSocket port)
	   cliSock (.accept servSock)
	   in (.getInputStream cliSock)
	   out (new FileOutputStream file)]
       (loop [c (.read in)]
	 (if (not (= -1 c))
	   (do
	     (.write out c)
	     (recur (.read in)))))
       (.close out)
       (.close in)
       (.close servSock)
       (.close cliSock))))

(defn speed-test []
  (let [f "test.dat"
	o "test.dat.arrived"
	servThread (new Thread (fn [] (recv-from o)))]
    (.run servThread)
    (time
     (send-to f 31023))))
    
	