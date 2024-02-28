import React, { useState, ChangeEvent } from "react";

interface JavaCodeInputProps {
  topic: string;
  cols: number;
}

function JavaCodeInput({ topic, cols }: JavaCodeInputProps) {
  const [javaCode, setJavaCode] = useState("");

  const handleChange = (event: ChangeEvent<HTMLTextAreaElement>) => {
    setJavaCode(event.target.value);
  };
  

  // Calculate the number of rows based on the length of javaCode
  const calculateRows = (text: string) => {
    const numberOfLines = text.split(/\r\n|\r|\n/).length;
    // Assuming a standard line height
    const lineHeight = 16; // not use yet
    const rows = Math.max(3, numberOfLines);
    return rows;
  };

  const rows = calculateRows(javaCode);

  return (
    <div style={{ marginBottom: 20 }}>
      <h4 style={{ marginTop: 20, marginBottom: 20 }}>{topic}</h4>
      <textarea
        value={javaCode}
        onChange={handleChange}
        rows={rows}
        cols={cols}
        placeholder="Enter your Java code here"
      />
      <div>
        <button
          style={{ cursor: "pointer" }}
          onClick={() => console.log(javaCode)} //javacode = all input
        >
          Submit
        </button>
      </div>
    </div>
  );
}

export default JavaCodeInput;
