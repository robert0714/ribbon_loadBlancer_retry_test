# ribbon_loadBlancer_retry_test

# Senario
當Eureka client的服務兩個實體中，其中一個掛掉時，其他服務在透過eureka 呼叫時，是否會跟nginx一樣遇到壞掉就會換下一個實體發送（有兩難，時間過太久，有時真的是處理太久）
