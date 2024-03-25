import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";

const Buttons: NextPage = () => {
  const router = useRouter();

  const onPLAYContainerClick = useCallback(() => {
    router.push("/login"); // Assuming gameplay page is at "/gameplay"
  }, [router]);

  const onRULESContainerClick = useCallback(() => {
    router.push("/the-rule"); // Assuming rules page is at "/the-rule"
  }, [router]);

  const onConfigContainerClick = useCallback(() => {
    router.push("/config"); // Assuming config page is at "/config"
  }, [router]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center", // Center content vertically
      }}
    >
      {/* Play button */}
      <button
        className="cursor-pointer text-white text-shadow-lg bg-gradient-to-r from-green-500 to-teal-500 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-teal-800 font-bold rounded-md text-7xl px-12 py-5 shadow-md transition ease-in-out duration-300 transform hover:-translate-y-1 hover:scale-110 m-4"
        onClick={onPLAYContainerClick}
      >
        PLAY
      </button>

      {/* Rules button */}
      <button
        className="cursor-pointer text-white text-shadow-lg bg-gradient-to-r from-orange-500 to-yellow-500 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-yellow-800 font-bold rounded-md text-7xl px-12 py-5 shadow-md transition ease-in-out duration-300 transform hover:-translate-y-1 hover:scale-110 m-4" // Add margin-top for spacing
        onClick={onRULESContainerClick}
      >
        RULES
      </button>

      {/* Config button */}
      <button
        className=" cursor-pointer text-white text-shadow-lg bg-gradient-to-r from-blue-500 to-purple-500 hover:bg-gradient-to-l focus:ring-4 focus:outline-none focus:ring-purple-800 font-bold rounded-md text-7xl px-12 py-5 shadow-md transition ease-in-out duration-300 transform hover:-translate-y-1 hover:scale-110 m-4" // Add margin-top for spacing
        onClick={onConfigContainerClick}
      >
        CONFIG
      </button>
    </div>
  );
};

export default Buttons;
