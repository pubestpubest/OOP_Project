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
    router.push("/config");
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
                    <h2>หลักการในการเล่นเกมนี้</h2>
                    <p>
                      1 เกมนี้เป็นเกมที่มีเนื้อหาในแนว
                      แย่งการปกครองและช่วงชิงเงินและพื้นที่
                    </p>
                    <p>
                      2 เกมนี้มีหลักการในการเล่น ด้วย
                      การวางแผนด้วยการโยนคำสั่งมาทั้งก้อน (ConstructionPlan)
                      ที่เป็นเหมือนการวางแผนในการเล่นแต่ละเทรินๆ{" "}
                    </p>
                    <p>
                      3 สามารถกำหนดขนาดของแมพและรายละเอียดสำคัญอื่นๆได้ด้วย file
                      config
                    </p>
                    <p>
                      4 แมพที่เห็นในฝั่งขวา ในแต่ละ Cell ที่เป็น หกเหลี่ยมเล็กๆ
                      (Region) ประกอบด้วย ข้อมูล 4 อย่าง คือ budget
                      ผู้ที่มาลงทุน เจ้าของ และสีที่แสดงถึงเจ้าของนั้น
                    </p>

                    <p>
                      6 การจบเกม : คือผู้ใดมีเงินมากที่สุดหลังจากหมดเวลา หรือ
                      ผู้ที่เหลือรอดคนสุดท้าย
                      โดยสิ่งที่จะบอกว่าเราโดนคัดออกจากแผนที่คือการที่
                      เมืองหลวงของเราแตก
                    </p>
                  </div>
                </div>
                <div className="grayscale">
                  <HexGrid rows={5} columns={10} />
                </div>
              </div>
              <footer className="mt-10 text-white mx-[70px]">
                &nbsp;&nbsp;&nbsp;คำเตือน :
                เกมนี้เหมาะสำหรับผู้ที่สนใจในการเรียนหรือสนใจ ทางด้าน computer
                science เนื่องด้วยวิธีในการเล่นเกมจะยุ่งยากออกจากไปจากเกมทั่วไป
                เพราะได้ทำหลักการสอนต่างๆในเรื่องของ OOP,Webfrontend,Websocket,A
                bit compliler(parser)ในห้องมาใช้ในการทำเกม
              </footer>
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default TheRule;
