import { useState } from "react";

export const Task = ({ name, coin }) => {
  const [you, setYou] = useState(true);
  // setYou(true);
  return (
    <div>
      <div className=" flex-1  border-2 bg-white justify-center items-center rounded-lg h-[100 px] w-[150px]">
        <div
          className={
            you
              ? "border-2 bg-black justify-center items-center rounded-lg text-white p-3 text-10 "
              : "border-2 bg-white justify-center items-center rounded-lg text-black p-3 text-10"
          }
        >
          {"Name :"}
          {name}
        </div>
        <div className="flex">
          <button className=" border h-8 w-8 ml-3 my-3 mr-2 bg-yellow-400 rounded-full"></button>
          <div className="flex-col justify-center items-center mt-2 ">
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
