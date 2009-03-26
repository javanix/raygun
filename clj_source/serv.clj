(import '(java.io PrintWriter BufferedReader InputStreamReader File
		  FileWriter)
	'(java.net Socket ServerSocket)
	'(javax.swing JFileChooser JFrame JPanel))

; Put this in the "maybe bin"
;(defmacro de-nest [forms]
;  (let [all forms]
;    `(reduce (fn [a b] `(new ~b ~a)) all)))

;(defn 
;  #^{:doc "Provides a GUI 'open file' prompt"}
;  choose-file []
;  (let [file-chooser (doto (new JFileChooser)
;		       (.setCurrentDirectory (new File "."))
;		       (.showOpenDialog nil))]
;    (. file-chooser (getSelectedFile))))

; on second thought, this probably shouldn't be interactive
(defn 
  #^{:doc "Create a server that listens for incoming file transfers on
          port.  When a transfer is accepted, we verify the signature
          and then store in a temp file for encoding."}
  create-server
  ([] (create-server 31023))
  ([port]
     (let [servSock  (new ServerSocket port)
	   cliSock   (. servSock (accept))
	   outStream (new PrintWriter (. cliSock (getOutputStream)))
	   inStream  (new BufferedReader 
			  (new InputStreamReader
			       (. cliSock (getInputStream))))
	   tempFileStream  (new FileWriter
				(. File createTempFile "unencoded_data"
				   nil))]
       (.write tempFileStream (. inStream (read))))))
