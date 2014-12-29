#pragma once

#include <DNDGame.h>

class TrainRushGame : public DNDGame
{
public:
    TrainRushGame       (PBWindow*              window              ,
                         DNDGameInformation     info                );

    void GameLoad       (void                                       ) override;
    void GameStart      (void                                       ) override;
    void GameExit       (void                                       ) override;
    void GamePause      (void                                       ) override;
    void GameResume     (void                                       ) override;

    void Update         (const DNDGameTimer     delta               ) override;
    void Render         (double                 interpolationAmount ) override;
};
