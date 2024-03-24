import SyntaxHighlighter from "react-syntax-highlighter";
import { useState, useEffect } from "react";

export const PlanBlock = () => {
  const [constructionPlan, setConstructionPlan] = useState(false); // Initial state for construction plan visibility
  const [text, setText] = useState(""); // State for the text area value
  const [constructionPlanText, setConstructionPlanText] = useState(""); // State for the plan text      //this is plan

  const inputText = (event) => {
    setText(event.target.value); // Update text area state on change
  };

  const ReadConfigText = () => {
    // Implement logic for reading or processing the configuration text based on your needs
    setConstructionPlanText(text); // Update construction plan text with current text area value after processing (if needed)
    console.log("Read configuration text:", text); // Example log for testing
  };

  return (
    <div className="mr-3">
      <div
        className="flex justify-between items-center bg-teal-700  px-[80px]  rounded-md border-solid   border-y-emerald-500 shadow-l"
        onClick={() => setConstructionPlan(!constructionPlan)}
      >
        <p className="text-[25px] font-sans text-white font-bold">
          CONSTRUCTION PLAN
        </p>
      </div>
      <div
        className={` bg-slate-200 mx-[5px] mt-4 rounded-md border-solid  border-2 `}
      >
        <div className="m-3  hrounded-md border-solid  border-2 max-h-[75vh] max-w-[27vw] ">
          <SyntaxHighlighter
            language="javascript"
            className="h-[200px] mx-3"
            showLineNumbers={1}
          >
            {constructionPlanText}
          </SyntaxHighlighter>
        </div>

        <div className="flex flex-row justify-center items-center h-[120px]">
          <div className="">
            <textarea
              style={{ resize: "none" }}
              type="text"
              className="p-1 mx-2  rounded-md border-solid  border-2 h-[90px] w-[350px]  overflow-scroll  "
              placeholder="Enter your code here"
              onChange={inputText}
              value={text}
            />
          </div>
          <button
            className="bg-rose-500 rounded-md text-white h-[36px] w-[60px] hover:scale-110 mr-2"
            onClick={ReadConfigText}
          >
            SENT
          </button>
        </div>
      </div>
    </div>
  );
};
export default PlanBlock;
