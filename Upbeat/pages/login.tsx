import { useRouter } from "next/router";
import React from "react";
import { useState, useCallback, useEffect } from "react";

function Login() {
  const playerNames = [];
  const router = useRouter();
  const playerCount = 2;
  const [allPlayerJoined, setAllPlayersJoined] = useState(true);
  const [inputValue, setInputValue] = useState("");
  const onSubmitClick = useCallback(() => {
    router.push("/gameplay");
  }, [router]);
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  return (
    <div className="bg-gradient-to-r from-emerald-700 to-slate-400  h-screen">
      <div className=" text-white flex items-center justify-center pt-10 pb-40 text-41xl font-mono font-bold">
        Welcome
      </div>
      <div className=" flex items-center justify-center ">
        {/* Border for entire div (2px thick, gray-500) */}
        <div className="border-2 border-gray-500 p-5 pr-9 rounded-lg shadow-lg bg-white">
          {/* Border for inner div (2px thick, gray-300) */}
          <div className="mb-5 text-slate-700 text-7xl font-mono ">
            Your name!
          </div>
          <div>
            <input
              type="text"
              placeholder="Enter your name"
              className="w-full max-w-sm p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
              value={inputValue}
              onChange={handleChange}
            />
            <button
              className=" bg-emerald-500 hover:bg-emerald-800 text-white font-bold py-2 px-4 rounded-md mt-4 cursor-pointer"
              onClick={onSubmitClick}
            >
              SUBMIT
            </button>
            {inputValue}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
