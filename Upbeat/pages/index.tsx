import type { NextPage } from "next";
import Buttons from "../components/buttons"; // Assuming Buttons is a valid component
import Image from "next/image";

const Home: NextPage = () => {
  return (
    <div className="bg-gradient-to-r from-cyan-950 to-slate-400  h-screen">
      <div>
        <main>
          <section className="relative text-109xl flex italic font-extrabold font-inter text-white text-center items-center justify-center">
            <div className="mt-20 my-text">
              UP<a className=" text-yellow-400">BEAT</a>
            </div>
          </section>
          <div className="mt-20">
            <Buttons />
          </div>
        </main>
      </div>
    </div>
  );
};

export default Home;
