//
//  SkiWebController.swift
//  PROJECT3
//
//  Created by George Rossney on 12/14/16.
//  Copyright © 2016 George Rossney. All rights reserved.
//

import UIKit

class SkiWebController: UIViewController {
    
    @IBOutlet var webView: UIWebView!
    
    @IBOutlet var urlField: UITextField!

    //check the URL and access the webpage
    @IBAction func go(_ sender: Any) {
        let urlString = urlField.text!
        
        if let url = URL(string: urlString) {
            let request = URLRequest(url: url)
            webView.loadRequest(request)
        }
        else {
            //not a valid URL, can't access it
            print("Inavlid url: \(urlString)")
        }
    }
}
