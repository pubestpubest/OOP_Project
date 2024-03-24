import { useState } from "react";

export const Task = ({ name, coin, YesYou }) => {
  const [you, setYou] = useState(YesYou);
  // setYou(true);
  return (
    <div>
      <div className=" flex-1  border-2 bg-emerald-900 justify-center items-center rounded-lg h-[50 px] w-[150px] ">
        <div
          className={
            you
              ? "border-2 bg-black justify-center items-center rounded-lg text-white  text-10 p-4"
              : "border-2 bg-white justify-center items-center rounded-lg text-black  text-10 p-1"
          }
        >
          {you ? "You : " : "Name : "}
          {name}
        </div>
        <div className="flex">
          <button className=" border h-5 w-5 ml-3 my-3 mr-2 bg-yellow-400 rounded-full "></button>
          <div className="flex-col justify-center items-center mt-2 mb-2 text-white">
            <div className=" flex-row">
              <div>{"coin : "} </div>
              {coin}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Task;
