#include "TrainRush.h"
#include "TrainRushGame.h"

#include <DNDParserTexture.h>
#include <DNDParserVorbis.h>

#include <DNDKeyboardState.h>
#include <DNDMouseState.h>

#include <DNDManagerReflection.h>
#include <DNDManagerContent.h>
#include <DNDManagerScene.h>
#include <DNDManagerSound.h>
#include <DNDManagerTelemetry.h>

int CALLBACK    WinMain (HINSTANCE curInstance, HINSTANCE oldInstance, LPSTR cmdArgs, int cmdShow)
{
    auto window = new PBWindow(curInstance, NULL);
         window->Initialize();

         window->AddInputDevice(new PBInputDeviceMouse      ());
         window->AddInputDevice(new PBInputDeviceKeyboard   ());

    auto game   = new TrainRushGame(window, DNDGameInformation());

    // add managers

         game->AddManager(new DNDManagerReflection  (game));
         game->AddManager(new DNDManagerContent     (game));
         game->AddManager(new DNDManagerScene       (game));
         game->AddManager(new DNDManagerSound       (game));
      // game->AddManager(new DNDManagerTelemetry   (game));

    auto cm     = game->GetManager(SNOW_OWL_MANAGER_CONTENT);
    auto cml    = static_cast<DNDManagerContent*>(cm);

         cml->RegisterContentParser(new DNDParserVorbis         ());
         cml->RegisterContentParser(new DNDContentParserTexture ());

         game->Initialize   ();
         game->Start        ();

    do
    {
        if (window->ProcessEvents())
            continue;

        if (!window->IsActive())
            continue;

        game->Step();

    } while (window->IsRunning());

    game    ->Unload    ();
    window  ->Destroy   ();

    deletePointer(game      );
    deletePointer(window    );

    return 0;
}
