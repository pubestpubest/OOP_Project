import type { NextPage } from "next";
import { useCallback } from "react";
import { useRouter } from "next/router";
import HexGrid from "../components/HexGird";
const TheRule: NextPage = () => {
  const router = useRouter();

  const onButtonContainerClick = useCallback(() => {
    router.push("/");
  }, [router]);

  const onButtonContainer1Click = useCallback(() => {
    router.push("/gameplay");
  }, [router]);

  return (
    <div className="bg-gradient-to-t from-amber-600 to-slate-100  h-screen w-screen ">
      <div>
        <section className=" flex flex-row items-center justify-center  gap-[45vh] pt-10">
          <button
            onClick={onButtonContainerClick}
            className=" cursor-pointer border-solid border-2 rounded-xl p-5 focus:ring-4 focus:outline-none focus:ring-white font-bold  text-7xl px-6 py-4  transition ease-in-out  transform hover:-translate-y-1 hover:scale-110 m-4"
          >
            &lt;&lt; Home
          </button>

          <div className="text-41xl flex items-center justify-center text-white font-mono font-bold">
            <div className="mt-5 flex border-solid border-2 rounded-xl p-5 bg-gradient-to-r  from-orange-700 to-yellow-500 shadow-xl ">
              <div className="text-wrap"> THE RULES</div>
            </div>
          </div>
          <button
            onClick={onButtonContainer1Click}
            className="cursor-pointer border-solid border-2 rounded-xl p-5 focus:ring-4 focus:outline-none focus:ring-white font-bold  text-7xl px-6 py-4  transition ease-in-out  transform hover:-translate-y-1 hover:scale-110 m-4"
          >
            PLAY &gt;&gt;
          </button>
        </section>
        <div className="pt-10">
          <section className="self-stretch flex flex-row items-start justify-start ">
            <div>
              <div className="flex flex-row flex-wrap items-start justify-start mr-10">
                <div className="flex-1 px-10 flex-col items-start justify-start text-black ">
                  <div className="border-2 border-solid p-8 rounded-lg bg-zinc-300 bg-opacity-40">
                    <p>1</p>
                    <p>2</p>
                    <p>3</p>
                    <p>4</p>
                    <p>5</p>
                    <p>6</p>
                    <p>7</p>
                    <p>8</p>
                    <p>9</p>
                  </div>
                </div>
                <div className="grayscale">
                  <HexGrid rows={5} columns={10} />
                </div>
              </div>
              <footer className="mt-10 text-white mx-[70px]">
                &nbsp;&nbsp;&nbsp;คำเตือน : ความหวาน
                ดาวนับล้านบนฟ้ายังรู้สึกถึงใจฉัน ที่มันเป็นของเธออยู่อย่างนั้น
                เพลงรัก ช่างยากนักจะเขียนให้งดงาม เท่าความจริง
                สิ่งที่ฉันได้เจอฉันได้เจอ [Refrain]
                เธอปลอบโยนจากค่ำคืนเลวร้ายที่น่ากลัว ให้จางหาย กอดฉันไว้ใกล้ตัว
                อุ่นหัวใจ กล่อมนอน ด้วยถ้อยคำหวาน ๆ ที่เธอบอก ว่าจากนี้
                ฉันจะไม่เดียวดายอีกต่อไป [Chorus]
                เราจะอยู่ด้วยกันกระทั่งในฝันตอนหลับตา ก็จะมีเธอ มีเธออยู่ในนั้น
                เคียงกันในทุกวัน บอกฉันว่ารักอีกสักคำ
                ช่วยทำให้เพลงนี้หวานกว่าเดิม [Verse 2] ความสุขใจ
                ผลิบานในทุกครั้งที่มีเธอ ดั่งดอกไม้ ที่รอแสงตะวัน อย่างเช่นเธอ
                เพลงนี้ คงไม่มีความหมายสักเท่าไร
                หากไม่มีเธอเป็นท่วงทำนองต่อจากนี้
              </footer>
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default TheRule;
