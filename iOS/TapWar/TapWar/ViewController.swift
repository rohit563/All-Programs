//
//  ViewController.swift
//  TapWar
//
//  Created by Rohit Banda on 1/9/16.
//  Copyright © 2016 Rohit Banda. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var TpBtn: UIButton!
    @IBOutlet weak var BtmBtn: UIButton!
    @IBOutlet weak var TpLbl: UILabel!
    @IBOutlet weak var BtmLbl: UILabel!
    @IBOutlet weak var Play: UIButton!


    @IBOutlet weak var PlayButton: UIButton!

    @IBOutlet weak var EndScene: UIButton!
 
    
    
    var blueScore = 0
    var redScore = 0
    var seconds = 30
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        TpLbl.transform = CGAffineTransformMakeRotation(3.14)
        TpLbl.text = "\(blueScore)"
        BtmLbl.text = "\(redScore)"
        EndScene.hidden = true
        Play.hidden = true

//        finalScoreButton.hidden = true
//        blueEndScore.hidden = true
//        redEndScore.hidden = true
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func TpBtnAction(sender: AnyObject) {
        blueScore++
        TpLbl.text = "\(blueScore)"
        ScoreCheck()
    }
    @IBAction func BtmBtnAction(sender: AnyObject) {
        redScore++
        BtmLbl.text = "\(redScore)"
        ScoreCheck()
    }
    func ScoreCheck(){
        if(blueScore == 10)
        {
            EndScene.hidden = false
            EndScene.backgroundColor = UIColor.cyanColor()
            Play.hidden = false
            
        }
        else if(redScore == 10)
        {
            EndScene.hidden = false
            EndScene.backgroundColor = UIColor.redColor()
            Play.hidden = false
        }
    }


    @IBAction func PlayAction(sender: AnyObject) {
        EndScene.hidden = true
        Play.hidden = true
        blueScore = 0
        redScore = 0
        TpLbl.text = "\(blueScore)"
        BtmLbl.text = "\(redScore)"
    }


    @IBAction func EndSceneAction(sender: AnyObject) {
    }


}

